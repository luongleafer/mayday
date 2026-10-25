package mayday.controllers;

import lombok.RequiredArgsConstructor;
import mayday.dto.CreatePatientRequestDto;
import mayday.dto.CreatePatientResponseDto;
import mayday.dto.PatientSearchResponseDto;
import mayday.entities.Gender;
import mayday.services.BenhnhanService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/doctor/patients")
@RequiredArgsConstructor
public class BenhnhanController {

    private final BenhnhanService benhnhanService;

    @GetMapping({"", "/"})
    public ResponseEntity<PatientSearchResponseDto> searchPatients(
            @RequestParam(name = "keyword", required = false) String keyword,
            @RequestParam(name = "gender", required = false) Gender gender,
            @RequestParam(name = "min_year", required = false) Integer minYear,
            @RequestParam(name = "max_year", required = false) Integer maxYear,
            @RequestParam(name = "page", defaultValue = "1") int page,
            @RequestParam(name = "limit", defaultValue = "20") int limit
    ) {
        PatientSearchResponseDto response = benhnhanService.searchPatients(
                keyword, gender, minYear, maxYear, page, limit
        );
        return ResponseEntity.ok(response);
    }

    @PostMapping({"", "/"})
    public ResponseEntity<CreatePatientResponseDto> createPatient(@RequestBody CreatePatientRequestDto req) {
        CreatePatientResponseDto response = benhnhanService.createPatient(req);
        return ResponseEntity.ok(response);
    }
}
