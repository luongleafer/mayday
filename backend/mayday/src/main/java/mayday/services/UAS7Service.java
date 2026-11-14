package mayday.services;

import lombok.RequiredArgsConstructor;
import mayday.dto.UAS7EligibilityResponseDto;
import mayday.dto.requests.UAS7CreateRequestDto;
import mayday.dto.responses.UAS7CreateResponseDto;
import mayday.dto.responses.UAS7GetResponseDto;
import mayday.entities.NhatKyUASHangNgay;
import mayday.entities.hosobenhan.HoSoBenhAn;
import mayday.entities.nguoi.BenhNhan;
import mayday.repositories.BenhNhanRepository;
import mayday.repositories.HoSoBenhAnRepository;
import mayday.repositories.NhatKyUASHangNgayRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UAS7Service {
    private final HoSoBenhAnRepository hoSoBenhAnRepository;
    private final NhatKyUASHangNgayRepository nhatKyUASHangNgayRepository;
    private final BenhNhanRepository benhNhanRepository;

    //UAS701
    @Transactional(readOnly = true)
    public UAS7EligibilityResponseDto checkUas7Eligibility(Long maBenhNhan) {
        Optional<HoSoBenhAn> hoSoActive = hoSoBenhAnRepository.findActiveByMaBenhNhan(maBenhNhan);

        if (hoSoActive.isPresent()) {
            return UAS7EligibilityResponseDto.builder()
                    .code("1000")
                    .message("OK")
                    .data(UAS7EligibilityResponseDto.EligibilityData.builder()
                            .eligible("true")
                            .medicalRecordId(String.valueOf(hoSoActive.get().getMaHoSo()))
                            .build())
                    .build();
        }

        return UAS7EligibilityResponseDto.builder()
                .code("1000")
                .message("OK")
                .data(UAS7EligibilityResponseDto.EligibilityData.builder()
                        .eligible("false")
                        .medicalRecordId(null)
                        .build())
                .build();
    }
    //UAS702
    @Transactional()
    public UAS7CreateResponseDto uas7DailyCreate (Long maBenhNhan, UAS7CreateRequestDto request){
        LocalDate scoreDate;
        try {
            scoreDate = LocalDate.parse(request.getScoreDate());
        } catch (Exception e) {
            return buildError("2006", "Invalid score_date format (expected YYYY-MM-DD)");
        }
        if (nhatKyUASHangNgayRepository.existsByMaBenhNhanAndNgayChamDiem(maBenhNhan, scoreDate)) {
            return buildError("5002", "Score for date already submitted");
        }
        short iss7;
        try {
            iss7 = Short.parseShort(request.getIss7());
            if (iss7 < 0 || iss7 > 3) {
                return buildError("5003", "Invalid ISS7 value");
            }
        } catch (NumberFormatException e) {
            return buildError("5003", "Invalid ISS7 value");
        }


        short hss7;
        try {
            hss7 = Short.parseShort(request.getHss7());
            if (hss7 < 0 || hss7 > 3) {
                return buildError("5004", "Invalid HSS7 value");
            }
        } catch (NumberFormatException e) {
            return buildError("5004", "Invalid HSS7 value");
        }
        Long maHoSoMan = null;
        if (request.getMedicalRecordId() != null && !request.getMedicalRecordId().isBlank()) {
            try {
                maHoSoMan = Long.parseLong(request.getMedicalRecordId());
            } catch (NumberFormatException e) {
                return buildError("4001", "Invalid medical_record_id");
            }
        }

        // 6. Lưu vào bảng nhatkyuashangngay
        NhatKyUASHangNgay entity = NhatKyUASHangNgay.builder()
                .maBenhNhan(maBenhNhan)
                .maHoSoMan(maHoSoMan)
                .ngayChamDiem(scoreDate)
                .diemNguaIss(iss7)
                .diemSanPhuHss(hss7)
                .trangThaiNgay("HOP_LE") // Cột NOT NULL với check constraint ('HOP_LE', 'KHONG_HOP_LE')
                .build();

        NhatKyUASHangNgay saved = nhatKyUASHangNgayRepository.save(entity);


        int dailyScore = iss7 + hss7;
        String nowStr = OffsetDateTime.now().toString();
        String updatedAtStr = saved.getThoiDiemCapNhat() != null
                ? saved.getThoiDiemCapNhat().toString()
                : nowStr;

        UAS7CreateResponseDto.UAS7ScoreItemDto item = UAS7CreateResponseDto.UAS7ScoreItemDto.builder()
                .patientId(String.valueOf(maBenhNhan))
                .medicalRecordId(maHoSoMan != null ? String.valueOf(maHoSoMan) : null)
                .scoreDate(scoreDate.toString())
                .iss7(String.valueOf(iss7))
                .hss7(String.valueOf(hss7))
                .dailyScore(String.valueOf(dailyScore))
                .note(request.getNote())
                .createdAt(nowStr)
                .updatedAt(updatedAtStr)
                .build();

        return UAS7CreateResponseDto.builder()
                .code("1001")
                .message("Created successfully")
                .data(List.of(item))
                .build();
    }

    private UAS7CreateResponseDto buildError(String code, String message) {
        return UAS7CreateResponseDto.builder()
                .code(code)
                .message(message)
                .data(null)
                .build();
    }

    //UAS704
    @Transactional()
    public UAS7GetResponseDto getUas7HistoryByPatient(String maBenhNhanStr){
        Long maBenhNhan;
        try {
            maBenhNhan = Long.parseLong(maBenhNhanStr);
        } catch (NumberFormatException e) {
            return UAS7GetResponseDto.builder()
                    .code("3007")
                    .message("User not found")
                    .data(null)
                    .build();
        }

        if (!benhNhanRepository.existsById(maBenhNhan)) {
            return UAS7GetResponseDto.builder()
                    .code("3007")
                    .message("User not found")
                    .data(null)
                    .build();
        }

        List<NhatKyUASHangNgay> records = nhatKyUASHangNgayRepository
                .findByMaBenhNhanOrderByNgayChamDiemDesc(maBenhNhan);

        List<UAS7GetResponseDto.UAS7HistoryItemDto> data = records.stream()
                .map(r -> UAS7GetResponseDto.UAS7HistoryItemDto.builder()
                        .patientId(String.valueOf(r.getMaBenhNhan()))
                        .medicalRecordId(r.getMaHoSoMan() != null ? String.valueOf(r.getMaHoSoMan()) : null)
                        .scoreDate(r.getNgayChamDiem().toString())
                        .iss7(String.valueOf(r.getDiemNguaIss()))
                        .hss7(String.valueOf(r.getDiemSanPhuHss()))
                        .dailyScore(String.valueOf(r.getDiemUasNgay() != null
                                ? r.getDiemUasNgay()
                                : r.getDiemNguaIss() + r.getDiemSanPhuHss()))
                        .note(null)
                        .build())
                .toList();


        return UAS7GetResponseDto.builder()
                .code("1000")
                .message("OK")
                .data(data)
                .build();








    }





}
