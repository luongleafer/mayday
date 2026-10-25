package mayday.services;

import lombok.RequiredArgsConstructor;
import mayday.dto.PatientItemDto;
import mayday.dto.PatientSearchResponseDto;
import mayday.entities.Benhnhan;
import mayday.entities.Nguoi;
import mayday.repositories.BenhnhanRepository;
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
    private final BenhnhanRepository benhnhanRepository;

    @Transactional(readOnly = true)
    public PatientSearchResponseDto searchPatients(
            String keyword,
            String gender,
            Integer minYear,
            Integer maxYear,
            int page,
            int limit
    ){
        if (minYear !=null && (maxYear == null )) {
            maxYear = minYear;
        } else if (maxYear !=null && (minYear == null)){
            minYear = maxYear;
        }
        String searchKeyword = (keyword != null && !keyword.trim().isEmpty()) ? keyword.trim() : null;
        String searchGender = (gender != null && !gender.trim().isEmpty()) ? gender.trim() : null;

        int pageIndex = Math.max(0, page - 1);
        int pageSize = limit > 0 ? limit : 20;
        Pageable pageable = PageRequest.of(pageIndex, pageSize);

        Page<Benhnhan> benhnhanPage = benhnhanRepository.timKiemBenhNhan(
                searchKeyword,
                searchGender,
                minYear,
                maxYear,
                pageable
        );

        // Tạo một danh sách rỗng để chứa kết quả DTO
        List<PatientItemDto> danhSachDto = new ArrayList<>();

        // Lấy danh sách bệnh nhân từ Database ra để duyệt từng người
        List<Benhnhan> danhSachEntity = benhnhanPage.getContent();

        for (Benhnhan bn : danhSachEntity) {
            Nguoi ng = bn.getNguoi(); // Lấy thông tin người tương ứng

            // Lấy năm sinh từ ngày sinh
            Integer namSinh = null;
            if (ng.getNgaySinh() != null) {
                namSinh = ng.getNgaySinh().getYear();
            }

            // Tạo một DTO mới và gán giá trị sang
            PatientItemDto dto = new PatientItemDto();
            dto.setUserId(bn.getMaBenhNhan());
            dto.setFullName(ng.getHoTen());
            dto.setPhone(ng.getSoDienThoai());
            dto.setGender(ng.getGioiTinh());
            dto.setYearOfBirth(namSinh);

            // Bỏ vào danh sách
            danhSachDto.add(dto);
        }

        PatientSearchResponseDto response = new PatientSearchResponseDto();
        response.setCode("1000");
        response.setMessage("Thành công");
        response.setData(danhSachDto);
        response.setTotalRecords(String.valueOf(benhnhanPage.getTotalElements()));

        return response;



    }



}
