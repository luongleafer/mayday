package mayday.controllers;

import lombok.RequiredArgsConstructor;
import mayday.dto.ClassifyQuestionsResponseDto;
import mayday.dto.ClassifyRecordRequestDto;
import mayday.dto.ClassifyRecordResponseDto;
import mayday.services.MedicalRecordService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/medical_records")
@RequiredArgsConstructor
public class MedicalRecordController {

    private final MedicalRecordService medicalRecordService;

    @GetMapping("/classify-questions")
    public ResponseEntity<ClassifyQuestionsResponseDto> getClassifyQuestions() {
        return ResponseEntity.ok(medicalRecordService.getClassifyQuestions());
    }

    @PostMapping("/classify")
    public ResponseEntity<ClassifyRecordResponseDto> classify(
            @RequestBody ClassifyRecordRequestDto request
    ) {
        return ResponseEntity.ok(medicalRecordService.classifyRecord(request));

    }
}