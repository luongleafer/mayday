package mayday.medicalrecords;

import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import mayday.dto.responses.ApiResponse;

import java.util.List;

@RestController
@RequestMapping("/api/v1/medical_records")
public class MedicalRecordController {
    private final MedicalRecordService service;

    public MedicalRecordController(MedicalRecordService service) {
        this.service = service;
    }

    @GetMapping("/all")
    public ApiResponse<List<MedicalRecord>> findAll(
            @RequestParam(required = false) String status,
            @RequestParam(name = "patient_id", required = false) Long patientId,
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "20") int limit,
            Authentication user) {
        return ApiResponse.ok(service.findAll(new MedicalRecordQuery(status, patientId, page, limit), user));
    }

    @GetMapping("/{id}")
    public ApiResponse<MedicalRecord> findById(@PathVariable long id, Authentication user) {
        return ApiResponse.ok(service.findById(id, user));
    }

    @GetMapping("/{id}/summary")
    public ApiResponse<MedicalRecordSummary> summary(@PathVariable long id, Authentication user) {
        return ApiResponse.ok(service.summary(id, user));
    }

    @GetMapping("/patients/{id}/history")
    public ApiResponse<List<MedicalRecord>> history(
            @PathVariable long id,
            @RequestParam(required = false) String status,
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "20") int limit,
            Authentication user) {
        return ApiResponse.ok(service.history(new MedicalRecordQuery(status, id, page, limit), user));
    }
}
