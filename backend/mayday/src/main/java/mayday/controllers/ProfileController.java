package mayday.controllers;

import jakarta.validation.Valid;
import mayday.dto.requests.EditProfileRequest;
import mayday.dto.responses.ApiResponse;
import mayday.dto.responses.PatientProfileResponse;
import mayday.services.BenhNhanService;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

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
        return ApiResponse.ok(benhNhanService.getPatientProfile(userId));
    }

    @PutMapping
    @ResponseBody
    ApiResponse<PatientProfileResponse> editProfile(Authentication authentication, @RequestBody EditProfileRequest editProfileRequest) {
        Long userId = Long.parseLong(authentication.getName());
        return ApiResponse.ok(benhNhanService.editPatientProfile(userId, editProfileRequest));
    }
}
