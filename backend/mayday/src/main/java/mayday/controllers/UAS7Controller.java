package mayday.controllers;

import lombok.RequiredArgsConstructor;
import mayday.dto.UAS7EligibilityData;
import mayday.dto.requests.UAS7CreateRequestDto;
import mayday.dto.responses.ApiResponse;
import mayday.dto.UAS7ScoreItemDto;
import mayday.services.UAS7Service;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/uas7-scores")
@RequiredArgsConstructor
public class UAS7Controller {

    private final UAS7Service uas7Service;

    //uas701
    @GetMapping("/eligibility")
    public ResponseEntity<ApiResponse<UAS7EligibilityData>> getEligibility(Authentication authentication) {
        Long maBenhNhan = Long.parseLong(authentication.getName());
        return ResponseEntity.ok(uas7Service.checkUas7Eligibility(maBenhNhan));
    }

    // uas702
    @PostMapping
    public ResponseEntity<ApiResponse<List<UAS7ScoreItemDto>>> createScore(
            @RequestBody UAS7CreateRequestDto request,
            Authentication authentication) {
        Long maBenhNhan = Long.parseLong(authentication.getName());
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(uas7Service.uas7DailyCreate(maBenhNhan, request));
    }

    // uas704
    @GetMapping("/patient/{patient_id}")
    public ResponseEntity<ApiResponse<List<UAS7ScoreItemDto>>> getPatientHistory(
            @PathVariable("patient_id") String patientId) {
        return ResponseEntity.ok(uas7Service.getUas7HistoryByPatient(patientId));
    }
}