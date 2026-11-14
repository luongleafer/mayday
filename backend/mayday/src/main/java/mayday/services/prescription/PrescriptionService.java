package mayday.services.prescription;

import lombok.RequiredArgsConstructor;
import mayday.dto.requests.CreatePrescriptionRequestDto;
import mayday.dto.requests.PrescriptionItemRequestDto;
import mayday.dto.responses.CreatePrescriptionResponseDto;
import mayday.entities.hosobenhan.HoSoBenhAn;
import mayday.entities.prescription.Prescription;
import mayday.entities.prescription.PrescriptionItem;
import mayday.exceptions.ApiException;
import mayday.repositories.HoSoBenhAnRepository;
import mayday.repositories.PhienKhamRepository;
import mayday.repositories.PrescriptionRepository;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PrescriptionService {

    private final PrescriptionRepository prescriptionRepository;
    private final HoSoBenhAnRepository hoSoBenhAnRepository;
    private final PhienKhamRepository phienKhamRepository;

    @Transactional
    public CreatePrescriptionResponseDto createPrescription(
            CreatePrescriptionRequestDto request,
            Authentication user,
            Long doctorIdHeader
    ) {
        if (request == null || request.getMedicalRecordId() == null || request.getMedicalRecordId().trim().isEmpty()) {
            throw new ApiException("4001", HttpStatus.NOT_FOUND, "Medical record not found");
        }

        Long recordId;
        try {
            recordId = Long.parseLong(request.getMedicalRecordId().trim());
        } catch (NumberFormatException e) {
            throw new ApiException("4001", HttpStatus.NOT_FOUND, "Medical record not found");
        }

        HoSoBenhAn record = hoSoBenhAnRepository.findById(recordId)
                .orElseThrow(() -> new ApiException("4001", HttpStatus.NOT_FOUND, "Medical record not found"));

        // Xác định ID bác sĩ gọi API
        Long currentDoctorId = resolveDoctorId(user, doctorIdHeader);

        // Kiểm tra phân công bác sĩ nếu hồ sơ đã có phiên khám được chỉ định bác sĩ
        if (currentDoctorId != null && phienKhamRepository.existsByMaHoSo(recordId)) {
            boolean assigned = phienKhamRepository.existsByMaHoSoAndMaBacSi(recordId, currentDoctorId);
            if (!assigned) {
                throw new ApiException("3105", HttpStatus.FORBIDDEN, "Doctor not assigned");
            }
        }

        // Validate danh sách thuốc
        if (request.getItems() == null || request.getItems().isEmpty()) {
            throw new ApiException("8001", HttpStatus.BAD_REQUEST, "Medicines list cannot be empty");
        }

        for (PrescriptionItemRequestDto itemDto : request.getItems()) {
            if (itemDto.getMedicineName() == null || itemDto.getMedicineName().trim().isEmpty()) {
                throw new ApiException("8002", HttpStatus.BAD_REQUEST, "Medicine name is required");
            }
        }

        // Lưu đơn thuốc
        Prescription prescription = Prescription.builder()
                .medicalRecordId(recordId)
                .createdBy(currentDoctorId != null ? currentDoctorId : 1L)
                .note(request.getNote())
                .createdAt(OffsetDateTime.now())
                .updatedAt(OffsetDateTime.now())
                .build();

        for (PrescriptionItemRequestDto itemDto : request.getItems()) {
            PrescriptionItem item = PrescriptionItem.builder()
                    .medicineName(itemDto.getMedicineName().trim())
                    .dosage(itemDto.getDosage())
                    .frequency(itemDto.getFrequency())
                    .duration(itemDto.getDuration())
                    .instruction(itemDto.getInstruction())
                    .createdAt(OffsetDateTime.now())
                    .build();
            prescription.addItem(item);
        }

        Prescription saved = prescriptionRepository.save(prescription);

        // Map kết quả trả về
        List<CreatePrescriptionResponseDto.PrescriptionItemData> itemDataList = new ArrayList<>();
        for (PrescriptionItem item : saved.getItems()) {
            itemDataList.add(CreatePrescriptionResponseDto.PrescriptionItemData.builder()
                    .prescriptionItemId(String.valueOf(item.getId()))
                    .medicineName(item.getMedicineName())
                    .dosage(item.getDosage())
                    .frequency(item.getFrequency())
                    .duration(item.getDuration())
                    .instruction(item.getInstruction())
                    .build());
        }

        CreatePrescriptionResponseDto.PrescriptionData data = CreatePrescriptionResponseDto.PrescriptionData.builder()
                .prescriptionId(String.valueOf(saved.getId()))
                .medicalRecordId(String.valueOf(saved.getMedicalRecordId()))
                .createdBy(String.valueOf(saved.getCreatedBy()))
                .note(saved.getNote())
                .totalMedicines(String.valueOf(itemDataList.size()))
                .items(itemDataList)
                .createdAt(saved.getCreatedAt().format(DateTimeFormatter.ISO_OFFSET_DATE_TIME))
                .build();

        return CreatePrescriptionResponseDto.builder()
                .code("1001")
                .message("Tạo đơn thuốc thành công")
                .data(data)
                .build();
    }

    private Long resolveDoctorId(Authentication user, Long doctorIdHeader) {
        if (doctorIdHeader != null && doctorIdHeader > 0) {
            return doctorIdHeader;
        }
        if (user != null && user.getName() != null) {
            try {
                return Long.parseLong(user.getName());
            } catch (NumberFormatException ignored) {
            }
        }
        return 1L; // Fallback bác sĩ mặc định nếu không có token xác thực
    }
}
