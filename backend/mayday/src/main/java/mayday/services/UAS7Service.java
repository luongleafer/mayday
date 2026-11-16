package mayday.services;

import lombok.RequiredArgsConstructor;
import mayday.dto.requests.UAS7CreateRequestDto;
import mayday.dto.responses.ApiResponse;
import mayday.dto.UAS7EligibilityData;
import mayday.dto.UAS7ScoreItemDto;
import mayday.entities.NhatKyUASHangNgay;
import mayday.entities.hosobenhan.HoSoBenhAn;
import mayday.repositories.BenhNhanRepository;
import mayday.repositories.HoSoBenhAnRepository;
import mayday.repositories.NhatKyUASHangNgayRepository;
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

    // UAS701: Kiểm tra điều kiện chấm điểm
    @Transactional(readOnly = true)
    public ApiResponse<UAS7EligibilityData> checkUas7Eligibility(Long maBenhNhan) {
        Optional<HoSoBenhAn> hoSoActive = hoSoBenhAnRepository.findActiveByMaBenhNhan(maBenhNhan);

        if (hoSoActive.isPresent()) {
            UAS7EligibilityData data = UAS7EligibilityData.builder()
                    .eligible("true")
                    .medicalRecordId(String.valueOf(hoSoActive.get().getMaHoSo()))
                    .build();
            return ApiResponse.ok(data);
        }

        UAS7EligibilityData data = UAS7EligibilityData.builder()
                .eligible("false")
                .medicalRecordId(null)
                .build();
        return ApiResponse.ok(data);
    }

    // UAS702: Khởi tạo điểm UAS7 hằng ngày
    @Transactional
    public ApiResponse<List<UAS7ScoreItemDto>> uas7DailyCreate(Long maBenhNhan, UAS7CreateRequestDto request) {
        // 1.
        LocalDate scoreDate;
        try {
            if (request.getScoreDate() == null) {
                return new ApiResponse<>("2006", "Invalid score_date format (expected YYYY-MM-DD)", null);
            }
            scoreDate = LocalDate.parse(request.getScoreDate());
        } catch (Exception e) {
            return new ApiResponse<>("2006", "Invalid score_date format (expected YYYY-MM-DD)", null);
        }

        // 2. Kiểm tra trùng ngày chấm điểm
        if (nhatKyUASHangNgayRepository.existsByMaBenhNhanAndNgayChamDiem(maBenhNhan, scoreDate)) {
            return new ApiResponse<>("5002", "Score for date already submitted", null);
        }

        // 3.
        short iss7;
        try {
            if (request.getIss7() == null) {
                return new ApiResponse<>("5003", "Invalid ISS7 value", null);
            }
            iss7 = Short.parseShort(request.getIss7());
            if (iss7 < 0 || iss7 > 3) {
                return new ApiResponse<>("5003", "Invalid ISS7 value", null);
            }
        } catch (NumberFormatException e) {
            return new ApiResponse<>("5003", "Invalid ISS7 value", null);
        }

        // 4.
        short hss7;
        try {
            if (request.getHss7() == null) {
                return new ApiResponse<>("5004", "Invalid HSS7 value", null);
            }
            hss7 = Short.parseShort(request.getHss7());
            if (hss7 < 0 || hss7 > 3) {
                return new ApiResponse<>("5004", "Invalid HSS7 value", null);
            }
        } catch (NumberFormatException e) {
            return new ApiResponse<>("5004", "Invalid HSS7 value", null);
        }

        // 5. Parse medical_record_id
        Long maHoSoMan = null;
        if (request.getMedicalRecordId() != null && !request.getMedicalRecordId().isBlank()) {
            try {
                maHoSoMan = Long.parseLong(request.getMedicalRecordId());
            } catch (NumberFormatException e) {
                return new ApiResponse<>("4001", "Invalid medical_record_id", null);
            }
        }


        NhatKyUASHangNgay entity = NhatKyUASHangNgay.builder()
                .maBenhNhan(maBenhNhan)
                .maHoSoMan(maHoSoMan)
                .ngayChamDiem(scoreDate)
                .diemNguaIss(iss7)
                .diemSanPhuHss(hss7)
                .trangThaiNgay("HOP_LE")
                .build();

        NhatKyUASHangNgay saved = nhatKyUASHangNgayRepository.save(entity);

        int dailyScore = iss7 + hss7;
        String nowStr = OffsetDateTime.now().toString();
        String updatedAtStr = saved.getThoiDiemCapNhat() != null
                ? saved.getThoiDiemCapNhat().toString()
                : nowStr;

        UAS7ScoreItemDto item = UAS7ScoreItemDto.builder()
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

        return ApiResponse.created(List.of(item));
    }

    // UAS704: Truy xuất toàn bộ lịch sử điểm
    @Transactional(readOnly = true)
    public ApiResponse<List<UAS7ScoreItemDto>> getUas7HistoryByPatient(String maBenhNhanStr) {
        Long maBenhNhan;
        try {
            maBenhNhan = Long.parseLong(maBenhNhanStr);
        } catch (NumberFormatException e) {
            return new ApiResponse<>("3007", "User not found", null);
        }

        if (!benhNhanRepository.existsById(maBenhNhan)) {
            return new ApiResponse<>("3007", "User not found", null);
        }

        List<NhatKyUASHangNgay> records = nhatKyUASHangNgayRepository
                .findByMaBenhNhanOrderByNgayChamDiemDesc(maBenhNhan);

        List<UAS7ScoreItemDto> data = records.stream()
                .map(r -> UAS7ScoreItemDto.builder()
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

        return ApiResponse.ok(data);
    }
}