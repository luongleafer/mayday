package mayday.controllers.lab;

import lombok.RequiredArgsConstructor;
import mayday.dto.requests.CreateLabRequestDto;
import mayday.dto.responses.CreateLabResponseDto;
import mayday.dto.responses.LabResultsResponseDto;
import mayday.services.lab.LabService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class LabController {

    private final LabService labService;

    // lab01: create_lab_request
    @PostMapping({"/lab-requests", "/lab-requests/"})
    public ResponseEntity<CreateLabResponseDto> createLabRequest(
            @RequestBody CreateLabRequestDto request,
            Authentication user,
            @RequestHeader(value = "X-Doctor-Id", required = false) Long doctorIdHeader
    ) {
        CreateLabResponseDto response = labService.createLabRequest(request, user, doctorIdHeader);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    // lab02: get_lab_results
    @GetMapping({"/lab-results/{recordId}", "/lab-results/{recordId}/"})
    public ResponseEntity<LabResultsResponseDto> getLabResults(
            @PathVariable("recordId") Long recordId,
            Authentication user,
            @RequestHeader(value = "X-Patient-Id", required = false) Long patientIdHeader
    ) {
        LabResultsResponseDto response = labService.getLabResults(recordId, user, patientIdHeader);
        return ResponseEntity.ok(response);
    }
}
