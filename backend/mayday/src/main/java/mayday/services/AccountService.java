package mayday.services;

import mayday.dto.requests.SignUpRequest;
import mayday.dto.requests.StaffCreationRequest;
import mayday.entities.cosovatchat.ChiNhanh;
import mayday.entities.cosovatchat.KhoaPhong;
import mayday.entities.cosovatchat.PhongKham;
import mayday.entities.nguoi.Nguoi;
import mayday.entities.nguoi.NhanVien;
import mayday.entities.nguoi.TaiKhoan;
import mayday.entities.nguoi.VaiTro;
import mayday.exceptions.NotFoundException;
import mayday.exceptions.PasswordMismatchException;
import mayday.exceptions.TopologyMismatchException;
import mayday.exceptions.UserExistsException;
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

    public AccountService(
            PasswordEncoder passwordEncoder,
            TaiKhoanRepository taiKhoanRepository,
            NguoiRepository nguoiRepository,
            VaiTroRepository vaiTroRepository,
            ChiNhanhRepository chiNhanhRepository,
            KhoaPhongRepository khoaPhongRepository,
            PhongKhamRepository phongKhamRepository,
            NhanVienRepository nhanVienRepository
    ) {
        this.passwordEncoder = passwordEncoder;
        this.taiKhoanRepository = taiKhoanRepository;
        this.nguoiRepository = nguoiRepository;
        this.vaiTroRepository = vaiTroRepository;
        this.chiNhanhRepository = chiNhanhRepository;
        this.khoaPhongRepository = khoaPhongRepository;
        this.phongKhamRepository = phongKhamRepository;
        this.nhanVienRepository = nhanVienRepository;
    }

    private VaiTro getDefaultRole() throws Exception {
        VaiTro benhNhan;
        benhNhan = vaiTroRepository.findByTenVaiTro("benh_nhan").orElse(null);
        if (benhNhan != null) return benhNhan;
        else throw new Exception("Default role not found");
    }


    @Transactional
    public TaiKhoan signUp(SignUpRequest signUpRequest, VaiTro vaiTro) throws Exception {
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
    public TaiKhoan createStaff(StaffCreationRequest request) throws Exception {
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
        if (!khoaPhong.getBranch().equals(chiNhanh))
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
            if (!phongKham.getDepartment().equals(khoaPhong))
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

}
