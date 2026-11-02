package mayday.services;

import lombok.RequiredArgsConstructor;
import mayday.dto.requests.CreatePatientRequestDto;
import mayday.dto.responses.CreatePatientResponseDto;
import mayday.dto.PatientItemDto;
import mayday.dto.responses.PatientSearchResponseDto;
import mayday.entities.nguoi.*;
import mayday.repositories.BenhnhanRepository;
import mayday.repositories.NguoiRepository;
import mayday.repositories.TaikhoanRepository;
import mayday.repositories.VaitroRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BenhnhanService {
    private final NguoiRepository nguoiRepository;
    private final BenhnhanRepository benhnhanRepository;
    private final TaikhoanRepository taikhoanRepository;
    private final VaitroRepository vaitroRepository;

    @Transactional(readOnly = true)
    public PatientSearchResponseDto searchPatients(
            String keyword,
            Gender gender,
            Integer minYear,
            Integer maxYear,
            int page,
            int limit
    ) {
        if (minYear != null && maxYear == null) {
            maxYear = minYear;
        } else if (maxYear != null && minYear == null) {
            minYear = maxYear;
        }
        String searchKeyword = (keyword != null && !keyword.trim().isEmpty()) ? keyword.trim() : null;
        int pageIndex = Math.max(0, page - 1);
        int pageSize = limit > 0 ? limit : 20;
        Pageable pageable = PageRequest.of(pageIndex, pageSize);

        Page<Benhnhan> benhnhanPage = benhnhanRepository.timKiemBenhNhan(
                searchKeyword,
                gender,
                minYear,
                maxYear,
                pageable
        );

        List<PatientItemDto> danhSachDto = new ArrayList<>();
        List<Benhnhan> danhSachEntity = benhnhanPage.getContent();

        for (Benhnhan bn : danhSachEntity) {
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
        response.setTotalRecords(String.valueOf(benhnhanPage.getTotalElements()));

        return response;
    }

    @Transactional
    public CreatePatientResponseDto createPatient(CreatePatientRequestDto req) {
        // 1. Kiểm tra số điện thoại đã tồn tại
        if (taikhoanRepository.existsByTenDangNhap(req.getPhone())) {
            return CreatePatientResponseDto.builder()
                    .code("3006")
                    .message("User phone already exists.")
                    .data(null)
                    .build();
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
        Benhnhan benhnhan = new Benhnhan();
        benhnhan.setNguoi(nguoiDaLuu); // @MapsId tự gán ma_benh_nhan = ma_nguoi
        benhnhan.setMaHis(req.getHisCode());
        benhnhan.setNgheNghiep(req.getOccupation());
        benhnhan.setDanToc(req.getEthnic());
        benhnhan.setBhytSoThe(req.getInsuranceCardNumber());
        benhnhan.setBhytGiaTriDen(req.getInsuranceExpireDate());
        benhnhan.setNguoiNhaHoTenDiaChi(req.getGuardianInfo());
        benhnhan.setNguoiNhaSoDienThoai(req.getGuardianPhone());

        Benhnhan benhnhanDaLuu = benhnhanRepository.save(benhnhan);

        // 4. Lấy vai trò Bệnh nhân (đưa ra ngoài để lấy roleId cho response)
        Vaitro vaiTroBenhNhan = vaitroRepository.findByTenVaiTro("BENH_NHAN")
                .orElseGet(() -> {
                    Vaitro role = new Vaitro();
                    role.setTenVaiTro("BENH_NHAN");
                    role.setTenHienThi("Bệnh nhân");
                    return vaitroRepository.save(role);
                });

        // 5. Lưu tài khoản nếu có mật khẩu khởi tạo[cite: 2]
        Taikhoan taikhoanDaLuu = null;
        if (req.getPassword() != null && !req.getPassword().isBlank()) {
            Taikhoan taikhoan = new Taikhoan();
            taikhoan.setNguoi(nguoiDaLuu);
            taikhoan.setVaiTro(vaiTroBenhNhan);
            taikhoan.setTenDangNhap(req.getPhone());
            taikhoan.setMatKhauMaHoa(req.getPassword());
            taikhoan.setTrangThaiHoatDong(true);

            taikhoanDaLuu = taikhoanRepository.save(taikhoan);
        }

        // 6. Đóng gói dữ liệu trả về
        CreatePatientResponseDto.PatientData data = CreatePatientResponseDto.PatientData.builder()
                .userId(benhnhanDaLuu.getMaBenhNhan())
                .roleId(vaiTroBenhNhan != null ? vaiTroBenhNhan.getMaVaiTro() : null)
                .fullName(nguoiDaLuu.getHoTen())
                .dateOfBirth(nguoiDaLuu.getNgaySinh())
                .gender(nguoiDaLuu.getGioiTinh())
                .phone(nguoiDaLuu.getSoDienThoai())
                .province(nguoiDaLuu.getDiaChiTinhTp())
                .ward(nguoiDaLuu.getDiaChiXaPhuong())
                .addressDetail(nguoiDaLuu.getDiaChiSoNhaThonPho())
                .hisCode(benhnhanDaLuu.getMaHis())
                .occupation(benhnhanDaLuu.getNgheNghiep())
                .ethnic(benhnhanDaLuu.getDanToc())
                .insuranceCardNumber(benhnhanDaLuu.getBhytSoThe())
                .insuranceExpireDate(benhnhanDaLuu.getBhytGiaTriDen())
                .guardianInfo(benhnhanDaLuu.getNguoiNhaHoTenDiaChi())
                .guardianPhone(benhnhanDaLuu.getNguoiNhaSoDienThoai())
                .createdAt(taikhoanDaLuu != null ? taikhoanDaLuu.getThoiDiemTao() : null)
                .build();

        return CreatePatientResponseDto.builder()
                .code("1001")
                .message("Tạo bệnh nhân thành công")
                .data(data)
                .build();
    }
}