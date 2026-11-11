package mayday.controllers.hosobenhan;

import mayday.dto.responses.ApiResponse;
import mayday.dto.requests.ChotCaKhamRequestBody;
import mayday.dto.responses.MedicalRecordsAnswersResponse;
import mayday.dto.responses.CreditMedicalRecordsResponse;
import mayday.dto.responses.SetAcceptMedicalRecordsResponse;
import mayday.services.hosobenhan.MedicalRecordManagementService;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/v1/medical_records")
public class MedicalRecordManagementController {

    private MedicalRecordManagementService medicalRecordManagementService;

    public MedicalRecordManagementController(MedicalRecordManagementService service) {
        medicalRecordManagementService = service;
    }

    // medrec07
    @PostMapping("/{id}/accept")
    ApiResponse<SetAcceptMedicalRecordsResponse> setAcceptMedicalRecords(@PathVariable Long id) {
        return medicalRecordManagementService.setAcceptMedicalRecords(id);
    }

    //medrec11
    @PutMapping("/{id}")
    ApiResponse<CreditMedicalRecordsResponse> creditMedicalRecords(@PathVariable Long id, @RequestBody ChotCaKhamRequestBody body) {
        return medicalRecordManagementService.creditMedicalRecords(id, body.trangThai(), body.danDo());
    }

    //medrec14
    @DeleteMapping("/{id}")
    ApiResponse<Void> deleteMedicalRecords(@PathVariable Long id) {
        return medicalRecordManagementService.deleteMedicalRecord(id);
    }

    //medrec08
    @GetMapping("/{id}/answers")
    ApiResponse<MedicalRecordsAnswersResponse> getMedicalRecordsAnswers(@PathVariable Long id) {
        return medicalRecordManagementService.getMedicalRecordsAnswers(id);
    }
}
