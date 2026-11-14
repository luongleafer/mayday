package mayday.controllers;

import lombok.RequiredArgsConstructor;
import mayday.dto.UAS7EligibilityResponseDto;
import mayday.dto.requests.UAS7CreateRequestDto;
import mayday.dto.responses.UAS7CreateResponseDto;
import mayday.services.UAS7Service;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/uas7-scores/")
@RequiredArgsConstructor

public class UAS7Controller {
    private final UAS7Service uas7Service;

    @GetMapping("/eligibility")
    public ResponseEntity<UAS7EligibilityResponseDto> getUas7ScoresEligibility(Authentication authentication) {
        // Lấy maBenhNhan từ Authentication/JWT Token hiện tại
        // Ví dụ: Long maBenhNhan = ((CustomUserDetails) authentication.getPrincipal()).getMaBenhNhan();
        Long maBenhNhan = Long.parseLong(authentication.getName());

        UAS7EligibilityResponseDto response = uas7Service.checkUas7Eligibility(maBenhNhan);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/uas7-scores")


    public ResponseEntity<UAS7CreateResponseDto> create_uas7scores(@RequestBody Authentication authentication, UAS7CreateRequestDto request){
        Long maBenhNhan = Long.parseLong(authentication.getName());
        UAS7CreateResponseDto response = uas7Service.uas7DailyCreate(maBenhNhan, request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
}
