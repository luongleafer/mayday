package mayday.controllers;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import mayday.dto.requests.CreatePatientRequestDto;
import mayday.dto.responses.CreatePatientResponseDto;
import mayday.dto.responses.PatientSearchResponseDto;
import mayday.entities.nguoi.Gender;
import mayday.services.BenhNhanService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/doctor/patients")
@RequiredArgsConstructor
public class BenhNhanController {

    private final BenhNhanService benhNhanService;

    // docpat01: search_patients
    @GetMapping({"", "/"})
    public ResponseEntity<PatientSearchResponseDto> searchPatients(
            @RequestParam(name = "keyword", required = false) String keyword,
            @RequestParam(name = "gender", required = false) Gender gender,
            @RequestParam(name = "min_year", required = false) Integer minYear,
            @RequestParam(name = "max_year", required = false) Integer maxYear,
            @RequestParam(name = "page", defaultValue = "1") int page,
            @RequestParam(name = "limit", defaultValue = "20") int limit
    ) {
        PatientSearchResponseDto response = benhNhanService.searchPatients(
                keyword, gender, minYear, maxYear, page, limit
        );
        return ResponseEntity.ok(response);
    }

    // docpat02: create_patient
    @PostMapping({"", "/"})
    public ResponseEntity<CreatePatientResponseDto> createPatient(@Valid @RequestBody CreatePatientRequestDto req) {
        CreatePatientResponseDto response = benhNhanService.createPatient(req);
        return ResponseEntity.ok(response);
    }
}
