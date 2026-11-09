package mayday.controllers.hosobenhan;

import lombok.RequiredArgsConstructor;
import mayday.dto.requests.ClassifyRecordRequestDto;
import mayday.dto.requests.CreateMedicalRecordRequestDto;
import mayday.dto.responses.ClassifyQuestionsResponseDto;
import mayday.dto.responses.ClassifyRecordResponseDto;
import mayday.dto.responses.CreateMedicalRecordResponseDto;
import mayday.services.hosobenhan.TaoPhanLoaiBenhAnService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/medical_records")
@RequiredArgsConstructor
public class TaoPhanLoaiBenhAnController {

    private final TaoPhanLoaiBenhAnService taoPhanLoaiBenhAnService;

    @GetMapping("/classify-questions")
    public ResponseEntity<ClassifyQuestionsResponseDto> getClassifyQuestions() {
        return ResponseEntity.ok(taoPhanLoaiBenhAnService.getClassifyQuestions());
    }

    @PostMapping("/classify")
    public ResponseEntity<ClassifyRecordResponseDto> classify(
            @RequestBody ClassifyRecordRequestDto request
    ) {
        return ResponseEntity.ok(taoPhanLoaiBenhAnService.classifyRecord(request));

    }

    @PostMapping({"", "/"})
    public ResponseEntity<CreateMedicalRecordResponseDto> set_medicalrecords_create(
            @RequestBody CreateMedicalRecordRequestDto request
    ) {
        return ResponseEntity.ok(taoPhanLoaiBenhAnService.createMedicalRecord(request));

    }


}