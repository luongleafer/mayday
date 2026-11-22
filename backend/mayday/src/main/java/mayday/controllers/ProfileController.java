package mayday.controllers;

import mayday.dto.responses.ApiResponse;
import mayday.dto.responses.PatientProfileResponse;
import mayday.services.BenhNhanService;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping({"/api/v1/profile", "/api/profile"})
public class ProfileController {

    private final BenhNhanService benhNhanService;

    public ProfileController(BenhNhanService benhNhanService) {
        this.benhNhanService = benhNhanService;
    }

    @GetMapping
    @ResponseBody
    ApiResponse<PatientProfileResponse> getProfile(Authentication authentication) {
        Long userId = Long.parseLong(authentication.getName());
        return ApiResponse.ok(benhNhanService.getPatienProfile(userId));
    }
}
