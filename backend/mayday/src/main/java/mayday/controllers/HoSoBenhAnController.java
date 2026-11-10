package mayday.controllers;

import lombok.RequiredArgsConstructor;
import mayday.dto.MedicalRecord;
import mayday.dto.MedicalRecordQuery;
import mayday.dto.MedicalRecordSummary;
import mayday.dto.requests.ChotCaKhamRequestBody;
import mayday.dto.requests.ClassifyRecordRequestDto;
import mayday.dto.requests.CreateMedicalRecordRequestDto;
import mayday.dto.responses.ApiResponse;
import mayday.dto.responses.BenhAnResponse;
import mayday.dto.responses.ChotPhienKhamResponse;
import mayday.dto.responses.ClassifyQuestionsResponseDto;
import mayday.dto.responses.ClassifyRecordResponseDto;
import mayday.dto.responses.CreateMedicalRecordResponseDto;
import mayday.dto.responses.NhanPhienKhamResponse;
import mayday.services.HoSoBenhAnService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/medical_records")
@RequiredArgsConstructor
public class HoSoBenhAnController {

    private final HoSoBenhAnService hoSoBenhAnService;

    // medrec01: get_medicalrecords_classifyquestions
    @GetMapping("/classify-questions")
    public ResponseEntity<ClassifyQuestionsResponseDto> getMedicalrecordsClassifyquestions() {
        return ResponseEntity.ok(hoSoBenhAnService.getMedicalrecordsClassifyquestions());
    }

    // medrec02: set_medicalrecords_classify
    @PostMapping("/classify")
    public ResponseEntity<ClassifyRecordResponseDto> setMedicalrecordsClassify(
            @RequestBody ClassifyRecordRequestDto request,
            Authentication user
    ) {
        return ResponseEntity.ok(hoSoBenhAnService.setMedicalrecordsClassify(request, user));
    }

    // medrec03: set_medicalrecords_create
    @PostMapping({"", "/"})
    public ResponseEntity<CreateMedicalRecordResponseDto> setMedicalrecordsCreate(
            @RequestBody CreateMedicalRecordRequestDto request,
            Authentication user
    ) {
        return ResponseEntity.ok(hoSoBenhAnService.setMedicalrecordsCreate(request, user));
    }

    // medrec04: get_medicalrecords_all
    @GetMapping("/all")
    public ApiResponse<List<MedicalRecord>> getMedicalrecordsAll(
            @RequestParam(required = false) String status,
            @RequestParam(name = "patient_id", required = false) Long patientId,
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "20") int limit,
            Authentication user
    ) {
        return ApiResponse.ok(hoSoBenhAnService.getMedicalrecordsAll(new MedicalRecordQuery(status, patientId, page, limit), user));
    }

    // medrec05: get_medicalrecords_id
    @GetMapping("/{id}")
    public ApiResponse<MedicalRecord> getMedicalrecordsId(@PathVariable long id, Authentication user) {
        return ApiResponse.ok(hoSoBenhAnService.getMedicalrecordsId(id, user));
    }

    // medrec06: get_medicalrecords_summary
    @GetMapping("/{id}/summary")
    public ApiResponse<MedicalRecordSummary> getMedicalrecordsSummary(@PathVariable long id, Authentication user) {
        return ApiResponse.ok(hoSoBenhAnService.getMedicalrecordsSummary(id, user));
    }

    // medrec13: get_history_medicalrecords
    @GetMapping("/patients/{id}/history")
    public ApiResponse<List<MedicalRecord>> getHistoryMedicalrecords(
            @PathVariable long id,
            @RequestParam(required = false) String status,
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "20") int limit,
            Authentication user
    ) {
        return ApiResponse.ok(hoSoBenhAnService.getHistoryMedicalrecords(new MedicalRecordQuery(status, id, page, limit), user));
    }

    // medrec07: set_accept_medicalrecords
    @PostMapping("/{id}/accept")
    public ApiResponse<NhanPhienKhamResponse> setAcceptMedicalrecords(@PathVariable Long id, Authentication user) {
        return hoSoBenhAnService.setAcceptMedicalrecords(id, user);
    }

    // medrec11: credit_medicalrecords
    @PutMapping("/{id}")
    public ApiResponse<ChotPhienKhamResponse> creditMedicalrecords(
            @PathVariable Long id,
            @RequestBody ChotCaKhamRequestBody body,
            Authentication user
    ) {
        return hoSoBenhAnService.creditMedicalrecords(id, body.trangThai(), body.danDo(), user);
    }

    // medrec14: delete_medicalrecords
    @DeleteMapping("/{id}")
    public ApiResponse<Void> deleteMedicalrecords(@PathVariable Long id, Authentication user) {
        return hoSoBenhAnService.deleteMedicalrecords(id, user);
    }

    // medrec08: get_medicalrecords_answers
    @GetMapping("/{id}/answers")
    public ApiResponse<BenhAnResponse> getMedicalrecordsAnswers(@PathVariable Long id, Authentication user) {
        return hoSoBenhAnService.getMedicalrecordsAnswers(id, user);
    }
}
