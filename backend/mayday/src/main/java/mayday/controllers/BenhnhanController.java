package mayday.controllers;

import lombok.RequiredArgsConstructor;
import mayday.dto.PatientSearchResponseDto;
import mayday.services.BenhnhanService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/doctor/patients")
@RequiredArgsConstructor
public class BenhnhanController {

    private final BenhnhanService benhnhanService;

    @GetMapping({"", "/"})
    public ResponseEntity<PatientSearchResponseDto> searchPatients(
            @RequestParam(name = "keyword", required = false) String keyword,
            @RequestParam(name = "gender", required = false) String gender,
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
}
