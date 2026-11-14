package mayday.services.lab;

import lombok.RequiredArgsConstructor;
import mayday.dto.requests.CreateLabRequestDto;
import mayday.dto.responses.CreateLabResponseDto;
import mayday.dto.responses.LabResultsResponseDto;
import mayday.entities.hosobenhan.HoSoBenhAn;
import mayday.entities.lab.LabRequest;
import mayday.entities.lab.LabRequestItem;
import mayday.entities.lab.LabResult;
import mayday.exceptions.ApiException;
import mayday.repositories.HoSoBenhAnRepository;
import mayday.repositories.LabRequestRepository;
import mayday.repositories.PhienKhamRepository;
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
public class LabService {

    private final LabRequestRepository labRequestRepository;
    private final HoSoBenhAnRepository hoSoBenhAnRepository;
    private final PhienKhamRepository phienKhamRepository;

    @Transactional
    public CreateLabResponseDto createLabRequest(
            CreateLabRequestDto request,
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

        hoSoBenhAnRepository.findById(recordId)
                .orElseThrow(() -> new ApiException("4001", HttpStatus.NOT_FOUND, "Medical record not found"));

        // Xác định ID bác sĩ chỉ định
        Long currentDoctorId = resolveDoctorId(user, doctorIdHeader);

        // Kiểm tra phân công bác sĩ nếu hồ sơ đã có phiên khám được chỉ định bác sĩ
        if (currentDoctorId != null && phienKhamRepository.existsByMaHoSo(recordId)) {
            boolean assigned = phienKhamRepository.existsByMaHoSoAndMaBacSi(recordId, currentDoctorId);
            if (!assigned) {
                throw new ApiException("3105", HttpStatus.FORBIDDEN, "Doctor not assigned");
            }
        }

        // Validate danh sách xét nghiệm
        if (request.getItems() == null || request.getItems().isEmpty()) {
            throw new ApiException("8101", HttpStatus.BAD_REQUEST, "Lab tests list cannot be empty");
        }

        for (CreateLabRequestDto.LabRequestItemDto itemDto : request.getItems()) {
            if (itemDto.getTestName() == null || itemDto.getTestName().trim().isEmpty()) {
                throw new ApiException("8102", HttpStatus.BAD_REQUEST, "Test name required");
            }
        }

        // Lưu phiếu yêu cầu xét nghiệm
        LabRequest labRequest = LabRequest.builder()
                .medicalRecordId(recordId)
                .requestedBy(currentDoctorId != null ? currentDoctorId : 1L)
                .status("pending")
                .note(request.getNote())
                .createdAt(OffsetDateTime.now())
                .updatedAt(OffsetDateTime.now())
                .build();

        for (CreateLabRequestDto.LabRequestItemDto itemDto : request.getItems()) {
            LabRequestItem item = LabRequestItem.builder()
                    .testName(itemDto.getTestName().trim())
                    .description(itemDto.getDescription())
                    .createdAt(OffsetDateTime.now())
                    .build();
            labRequest.addItem(item);
        }

        LabRequest saved = labRequestRepository.save(labRequest);

        // Map kết quả trả về
        List<CreateLabResponseDto.LabRequestItemData> itemDataList = new ArrayList<>();
        for (LabRequestItem item : saved.getItems()) {
            itemDataList.add(CreateLabResponseDto.LabRequestItemData.builder()
                    .id(String.valueOf(item.getId()))
                    .testName(item.getTestName())
                    .description(item.getDescription())
                    .build());
        }

        CreateLabResponseDto.LabRequestData data = CreateLabResponseDto.LabRequestData.builder()
                .id(String.valueOf(saved.getId()))
                .labRequestId(String.valueOf(saved.getId()))
                .medicalRecordId(String.valueOf(saved.getMedicalRecordId()))
                .requestedBy(String.valueOf(saved.getRequestedBy()))
                .status(saved.getStatus())
                .note(saved.getNote())
                .items(itemDataList)
                .createdAt(saved.getCreatedAt().format(DateTimeFormatter.ISO_OFFSET_DATE_TIME))
                .build();

        return CreateLabResponseDto.builder()
                .code("1001")
                .message("Tạo yêu cầu xét nghiệm thành công")
                .data(data)
                .build();
    }

    @Transactional(readOnly = true)
    public LabResultsResponseDto getLabResults(
            Long recordId,
            Authentication user,
            Long patientIdHeader
    ) {
        if (recordId == null || recordId <= 0) {
            throw new ApiException("4001", HttpStatus.NOT_FOUND, "Medical record not found");
        }

        HoSoBenhAn record = hoSoBenhAnRepository.findById(recordId)
                .orElseThrow(() -> new ApiException("4001", HttpStatus.NOT_FOUND, "Medical record not found"));

        // Kiểm tra quyền truy cập (RBAC): Nếu là bệnh nhân gọi API, chỉ xem được của chính mình
        Long callerPatientId = resolvePatientId(user, patientIdHeader);
        if (callerPatientId != null && record.getMaBenhNhan() != null) {
            if (!record.getMaBenhNhan().equals(callerPatientId)) {
                throw new ApiException("3106", HttpStatus.FORBIDDEN, "Cannot access another user's data");
            }
        }

        List<LabRequest> labRequests = labRequestRepository.findByMedicalRecordIdOrderByCreatedAtDesc(recordId);

        List<LabResultsResponseDto.LabRequestDetailData> requestDataList = new ArrayList<>();
        for (LabRequest req : labRequests) {
            List<LabResultsResponseDto.LabResultItemDetailData> itemDetailList = new ArrayList<>();
            for (LabRequestItem item : req.getItems()) {
                LabResult res = item.getResult();
                itemDetailList.add(LabResultsResponseDto.LabResultItemDetailData.builder()
                        .id(String.valueOf(item.getId()))
                        .testName(item.getTestName())
                        .resultId(res != null && res.getId() != null ? String.valueOf(res.getId()) : null)
                        .resultValue(res != null ? res.getResultValue() : null)
                        .concludedBy(res != null && res.getConcludedBy() != null ? String.valueOf(res.getConcludedBy()) : null)
                        .resultNote(res != null ? res.getResultNote() : null)
                        .build());
            }

            requestDataList.add(LabResultsResponseDto.LabRequestDetailData.builder()
                    .id(String.valueOf(req.getId()))
                    .requestedBy(String.valueOf(req.getRequestedBy()))
                    .status(req.getStatus())
                    .note(req.getNote())
                    .items(itemDetailList)
                    .createdAt(req.getCreatedAt().format(DateTimeFormatter.ISO_OFFSET_DATE_TIME))
                    .build());
        }

        return LabResultsResponseDto.builder()
                .code("1000")
                .message("Thành công")
                .data(requestDataList)
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
        return 1L;
    }

    private Long resolvePatientId(Authentication user, Long patientIdHeader) {
        if (patientIdHeader != null && patientIdHeader > 0) {
            return patientIdHeader;
        }
        if (user != null) {
            boolean isPatient = user.getAuthorities().stream()
                    .anyMatch(a -> "ROLE_PATIENT".equals(a.getAuthority()));
            if (isPatient && user.getName() != null) {
                try {
                    return Long.parseLong(user.getName());
                } catch (NumberFormatException ignored) {
                }
            }
        }
        return null;
    }
}
