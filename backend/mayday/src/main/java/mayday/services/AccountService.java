package mayday.services;

import mayday.dto.requests.SignUpRequest;
import mayday.dto.requests.StaffCreationRequest;
import mayday.dto.requests.UserAssignmentRequest;
import mayday.dto.responses.UserAssignmentResponse;
import mayday.entities.cosovatchat.ChiNhanh;
import mayday.entities.cosovatchat.KhoaPhong;
import mayday.entities.cosovatchat.PhongKham;
import mayday.entities.nguoi.*;
import mayday.exceptions.*;
import mayday.repositories.*;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AccountService {

    private final PasswordEncoder passwordEncoder;
    private final TaiKhoanRepository taiKhoanRepository;
    private final NguoiRepository nguoiRepository;
    private final VaiTroRepository vaiTroRepository;
    private final ChiNhanhRepository chiNhanhRepository;
    private final KhoaPhongRepository khoaPhongRepository;
    private final PhongKhamRepository phongKhamRepository;
    private final NhanVienRepository nhanVienRepository;
    private final BenhNhanRepository benhNhanRepository;

    public AccountService(
            PasswordEncoder passwordEncoder,
            TaiKhoanRepository taiKhoanRepository,
            NguoiRepository nguoiRepository,
            VaiTroRepository vaiTroRepository,
            ChiNhanhRepository chiNhanhRepository,
            KhoaPhongRepository khoaPhongRepository,
            PhongKhamRepository phongKhamRepository,
            NhanVienRepository nhanVienRepository,
            BenhNhanRepository benhNhanRepository
    ) {
        this.passwordEncoder = passwordEncoder;
        this.taiKhoanRepository = taiKhoanRepository;
        this.nguoiRepository = nguoiRepository;
        this.vaiTroRepository = vaiTroRepository;
        this.chiNhanhRepository = chiNhanhRepository;
        this.khoaPhongRepository = khoaPhongRepository;
        this.phongKhamRepository = phongKhamRepository;
        this.nhanVienRepository = nhanVienRepository;
        this.benhNhanRepository = benhNhanRepository;
    }

    private VaiTro getDefaultRole() {
        VaiTro benhNhan;
        benhNhan = vaiTroRepository.findByTenVaiTro("benh_nhan").orElse(null);
        if (benhNhan != null) return benhNhan;
        else throw new RuntimeException("Default role not found");
    }

    @Transactional
    public TaiKhoan signUp(SignUpRequest signUpRequest, Long roleId) {
        return signUp(signUpRequest, vaiTroRepository.findById(roleId).orElseThrow(() -> new NotFoundException("role", roleId)));
    }

    @Transactional
    public TaiKhoan signUp(SignUpRequest signUpRequest, VaiTro vaiTro) {
        if (taiKhoanRepository.existsByTenDangNhap(signUpRequest.getPhone()))
            throw new UserExistsException("User with username '" + signUpRequest.getPhone() + "' already exists.");
        if (vaiTro == null) vaiTro = getDefaultRole();
        Nguoi person = new Nguoi(signUpRequest);
        Nguoi newPerson = nguoiRepository.save(person);
        TaiKhoan account = new TaiKhoan(newPerson,
                                        signUpRequest.getPhone(),
                                        passwordEncoder.encode(signUpRequest.getPassword()),
                                        true,
                                        vaiTro);
        BenhNhan patient = new BenhNhan();
        patient.setNguoi(newPerson);
        benhNhanRepository.save(patient);
        return taiKhoanRepository.save(account);
    }

    @Transactional
    public void changePassword(Long accountId, String oldPassword, String newPassword) {
        TaiKhoan account = taiKhoanRepository.findById(accountId).orElse(null);
        if (account == null) return;
        String oldPasswordHash = account.getMatKhauMaHoa();
        if (!passwordEncoder.matches(oldPassword, oldPasswordHash))
            throw new PasswordMismatchException("");
        account.setMatKhauMaHoa(passwordEncoder.encode(newPassword));
        taiKhoanRepository.save(account);
    }

    @Transactional
    public TaiKhoan createStaff(StaffCreationRequest request) {
        ChiNhanh chiNhanh = chiNhanhRepository
                .findById(request.getBranchId())
                .orElseThrow(
                        () -> new NotFoundException("branch", request.getBranchId())
                );
        KhoaPhong khoaPhong = khoaPhongRepository
                .findById(request.getDepartmentId())
                .orElseThrow(
                        () -> new NotFoundException("department", request.getDepartmentId())
                );
        if (!khoaPhong.getChiNhanh().equals(chiNhanh))
            throw new TopologyMismatchException("Department with id " +
                                                        request.getDepartmentId() +
                                                        " does not belong to branch with id " +
                                                        request.getBranchId());
        PhongKham phongKham = null;
        Long roomId = request.getRoomId();
        if (roomId != null) {
            if (!phongKhamRepository.existsById(roomId)) throw new NotFoundException("room", roomId);
            phongKham = phongKhamRepository
                    .findById(roomId)
                    .orElseThrow(
                            () -> new NotFoundException("room", roomId)
                    );
            if (!phongKham.getKhoaPhong().equals(khoaPhong))
                throw new TopologyMismatchException("Room with id " +
                                                            roomId +
                                                            " does not belong to department with id " +
                                                            request.getDepartmentId());
        }
        VaiTro vaiTro = vaiTroRepository.findById(request.getRoleId()).orElseThrow(
                () -> new NotFoundException("role", request.getRoleId())
        );

        TaiKhoan newAccount = signUp(request, vaiTro);
        NhanVien nhanVien = new NhanVien();
        nhanVien.setNguoi(newAccount.getNguoi());
        nhanVien.setChiNhanh(chiNhanh);
        nhanVien.setKhoaPhong(khoaPhong);
        nhanVien.setPhongKham(phongKham);
        nhanVienRepository.save(nhanVien);
        return newAccount;
    }

    @Transactional
    public UserAssignmentResponse assignStaff(Long staffUserId, UserAssignmentRequest request) {
        TaiKhoan account = taiKhoanRepository.findById(staffUserId)
                .orElseThrow(
                        () -> new NotFoundException("user", staffUserId)
                );
        NhanVien staff = account.getNguoi().getNhanVien();
        if (staff == null) throw new NotFoundException("staff", account.getNguoi().getMaNguoi());
        ChiNhanh branch = chiNhanhRepository.findById(request.getBranchId())
                .orElseThrow(() ->
                                     new NotFoundException("branch", request.getBranchId())
                );
        KhoaPhong department = khoaPhongRepository.findById(request.getDepartmentId())
                .orElseThrow(
                        () -> new NotFoundException("department", request.getDepartmentId())
                );
        if (!department.getChiNhanh().equals(branch)) throw new InvalidRequestException("Invalid department in branch");
        staff.setChiNhanh(branch);
        staff.setKhoaPhong(department);
        if (request.getRoomId() != null) {
            PhongKham room = phongKhamRepository.findById(request.getRoomId()).orElseThrow(
                    () -> new NotFoundException(
                            "room",
                            request.getRoomId())
            );
            if (!room.getKhoaPhong().equals(department))
                throw new InvalidRequestException("Invalid room in department");
            staff.setPhongKham(room);
        }
        nhanVienRepository.save(staff);
        return new UserAssignmentResponse(staffUserId, request);
    }

}
