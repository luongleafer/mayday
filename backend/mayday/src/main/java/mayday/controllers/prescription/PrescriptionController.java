package mayday.controllers.prescription;

import lombok.RequiredArgsConstructor;
import mayday.dto.requests.CreatePrescriptionRequestDto;
import mayday.dto.responses.CreatePrescriptionResponseDto;
import mayday.services.prescription.PrescriptionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/prescriptions")
@RequiredArgsConstructor
public class PrescriptionController {

    private final PrescriptionService prescriptionService;

    // rx01: create_prescription
    @PostMapping({"", "/"})
    public ResponseEntity<CreatePrescriptionResponseDto> createPrescription(
            @RequestBody CreatePrescriptionRequestDto request,
            Authentication user,
            @RequestHeader(value = "X-Doctor-Id", required = false) Long doctorIdHeader
    ) {
        CreatePrescriptionResponseDto response = prescriptionService.createPrescription(request, user, doctorIdHeader);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
}
