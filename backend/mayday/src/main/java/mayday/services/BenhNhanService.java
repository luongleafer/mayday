package mayday.services;

import lombok.RequiredArgsConstructor;
import mayday.dto.PatientItemDto;
import mayday.dto.requests.CreatePatientRequestDto;
import mayday.dto.responses.CreatePatientResponseDto;
import mayday.dto.responses.PatientSearchResponseDto;
import mayday.entities.nguoi.*;
import mayday.exceptions.ConflictException;
import mayday.exceptions.InvalidRequestException;
import mayday.repositories.BenhNhanRepository;
import mayday.repositories.NguoiRepository;
import mayday.repositories.TaiKhoanRepository;
import mayday.repositories.VaiTroRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BenhNhanService {
    private final NguoiRepository nguoiRepository;
    private final BenhNhanRepository benhNhanRepository;
    private final TaiKhoanRepository taiKhoanRepository;
    private final VaiTroRepository vaiTroRepository;
    private final PasswordEncoder passwordEncoder;

    // docpat01: search_patients
    @Transactional(readOnly = true)
    public PatientSearchResponseDto searchPatients(
            String keyword,
            Gender gender,
            Integer minYear,
            Integer maxYear,
            int page,
            int limit
    ) {
        if (page < 1 || limit < 1 || limit > 100) {
            throw new InvalidRequestException("page must be >= 1 and limit must be between 1 and 100");
        }
        if (minYear != null && (minYear < 1900 || minYear > java.time.Year.now().getValue())
                || maxYear != null && (maxYear < 1900 || maxYear > java.time.Year.now().getValue())) {
            throw new InvalidRequestException("year must be valid");
        }
        if (minYear != null && maxYear != null && minYear > maxYear) {
            throw new InvalidRequestException("min_year must not exceed max_year");
        }
        if (minYear != null && maxYear == null) {
            maxYear = minYear;
        } else if (maxYear != null && minYear == null) {
            minYear = maxYear;
        }
        String searchKeyword = (keyword != null && !keyword.trim().isEmpty()) ? keyword.trim() : null;
        int pageIndex = Math.max(0, page - 1);
        int pageSize = limit > 0 ? limit : 20;
        Pageable pageable = PageRequest.of(pageIndex, pageSize);

        Page<BenhNhan> benhNhanPage = benhNhanRepository.timKiemBenhNhan(
                searchKeyword,
                gender,
                minYear,
                maxYear,
                pageable
        );

        List<PatientItemDto> danhSachDto = new ArrayList<>();
        List<BenhNhan> danhSachEntity = benhNhanPage.getContent();

        for (BenhNhan bn : danhSachEntity) {
            Nguoi ng = bn.getNguoi();

            Integer namSinh = null;
            if (ng.getNgaySinh() != null) {
                namSinh = ng.getNgaySinh().getYear();
            }

            PatientItemDto dto = new PatientItemDto();
            dto.setUserId(bn.getMaBenhNhan());
            dto.setFullName(ng.getHoTen());
            dto.setPhone(ng.getSoDienThoai());
            dto.setGender(ng.getGioiTinh());
            dto.setYearOfBirth(namSinh);

            danhSachDto.add(dto);
        }

        PatientSearchResponseDto response = new PatientSearchResponseDto();
        response.setCode("1000");
        response.setMessage("Thành công");
        response.setData(danhSachDto);
        response.setTotalRecords(String.valueOf(benhNhanPage.getTotalElements()));

        return response;
    }

    // docpat02: create_patient
    @Transactional
    public CreatePatientResponseDto createPatient(CreatePatientRequestDto req) {
        if (req == null) {
            throw new InvalidRequestException("request body is required");
        }
        if (taiKhoanRepository.existsByTenDangNhap(req.getPhone())) {
            throw ConflictException.duplicate("User phone already exists");
        }

        // 2. Lưu thông tin bảng 'nguoi'
        Nguoi nguoi = new Nguoi();
        nguoi.setHoTen(req.getFullName());
        nguoi.setNgaySinh(req.getDateOfBirth());
        nguoi.setGioiTinh(req.getGender());
        nguoi.setSoDienThoai(req.getPhone());
        nguoi.setDiaChiTinhTp(req.getProvince());
        nguoi.setDiaChiXaPhuong(req.getWard());
        nguoi.setDiaChiSoNhaThonPho(req.getAddressDetail());

        Nguoi nguoiDaLuu = nguoiRepository.save(nguoi);

        // 3. Lưu thông tin bảng 'benhnhan'
        BenhNhan benhNhan = new BenhNhan();
        benhNhan.setNguoi(nguoiDaLuu); // @MapsId tự gán ma_benh_nhan = ma_nguoi
        benhNhan.setMaHis(req.getHisCode());
        benhNhan.setNgheNghiep(req.getOccupation());
        benhNhan.setDanToc(req.getEthnic());
        benhNhan.setBhytSoThe(req.getInsuranceCardNumber());
        benhNhan.setBhytGiaTriDen(req.getInsuranceExpireDate());
        benhNhan.setNguoiNhaHoTenDiaChi(req.getGuardianInfo());
        benhNhan.setNguoiNhaSoDienThoai(req.getGuardianPhone());

        BenhNhan benhNhanDaLuu = benhNhanRepository.save(benhNhan);

        // 4. Lấy vai trò Bệnh nhân (đưa ra ngoài để lấy roleId cho response)
        VaiTro vaiTroBenhNhan = vaiTroRepository.findByTenVaiTro("BENH_NHAN")
                .orElseGet(() -> {
                    VaiTro role = new VaiTro();
                    role.setTenVaiTro("BENH_NHAN");
                    role.setTenHienThi("Bệnh nhân");
                    return vaiTroRepository.saveAndFlush(role);
                });

        // 5. Lưu tài khoản nếu có mật khẩu khởi tạo
        TaiKhoan taiKhoanDaLuu = null;
        if (req.getPassword() != null && !req.getPassword().isBlank()) {
            TaiKhoan taiKhoan = new TaiKhoan();
            taiKhoan.setNguoi(nguoiDaLuu);
            taiKhoan.setVaiTro(vaiTroBenhNhan);
            taiKhoan.setTenDangNhap(req.getPhone());
            taiKhoan.setMatKhauMaHoa(passwordEncoder.encode(req.getPassword()));
            taiKhoan.setTrangThaiHoatDong(true);

            taiKhoanDaLuu = taiKhoanRepository.save(taiKhoan);
        }

        // 6. Đóng gói dữ liệu trả về
        CreatePatientResponseDto.PatientData data = CreatePatientResponseDto.PatientData.builder()
                .userId(benhNhanDaLuu.getMaBenhNhan())
                .roleId(vaiTroBenhNhan != null ? vaiTroBenhNhan.getMaVaiTro() : null)
                .fullName(nguoiDaLuu.getHoTen())
                .dateOfBirth(nguoiDaLuu.getNgaySinh())
                .gender(nguoiDaLuu.getGioiTinh())
                .phone(nguoiDaLuu.getSoDienThoai())
                .province(nguoiDaLuu.getDiaChiTinhTp())
                .ward(nguoiDaLuu.getDiaChiXaPhuong())
                .addressDetail(nguoiDaLuu.getDiaChiSoNhaThonPho())
                .hisCode(benhNhanDaLuu.getMaHis())
                .occupation(benhNhanDaLuu.getNgheNghiep())
                .ethnic(benhNhanDaLuu.getDanToc())
                .insuranceCardNumber(benhNhanDaLuu.getBhytSoThe())
                .insuranceExpireDate(benhNhanDaLuu.getBhytGiaTriDen())
                .guardianInfo(benhNhanDaLuu.getNguoiNhaHoTenDiaChi())
                .guardianPhone(benhNhanDaLuu.getNguoiNhaSoDienThoai())
                .createdAt(taiKhoanDaLuu != null ? taiKhoanDaLuu.getThoiDiemTao() : null)
                .build();

        return CreatePatientResponseDto.builder()
                .code("1000")
                .message("Tạo bệnh nhân thành công")
                .data(data)
                .build();
    }
}
