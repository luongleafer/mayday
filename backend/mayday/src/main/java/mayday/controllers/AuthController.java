package mayday.controllers;

import mayday.dto.requests.LoginRequestBody;
import mayday.dto.responses.ApiResponse;
import mayday.services.AuthService;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth")
@ResponseBody
public class AuthController {

    @PostMapping("/login")
    public ApiResponse<Object> login(Authentication authentication) {
//        return ApiResponse.ok(authentication.getPrincipal());
        return ApiResponse.ok("what");
    }


}
