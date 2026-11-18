package mayday.controllers;

import jakarta.validation.Valid;
import jakarta.validation.ValidationException;
import mayday.dto.requests.ChangePasswordRequest;
import mayday.dto.requests.SignUpRequest;
import mayday.dto.responses.ApiResponse;
import mayday.dto.responses.SignUpResponseData;
import mayday.entities.nguoi.TaiKhoan;
import mayday.exceptions.PasswordMismatchException;
import mayday.exceptions.UserExistsException;
import mayday.security.AccountDetails;
import mayday.services.AccountService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/auth")
public class AccountController {

    private final AccountService accountService;

    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @PostMapping("/signup")
    public ResponseEntity<ApiResponse<SignUpResponseData>> signUp(@RequestBody @Valid SignUpRequest signUpRequest) throws Exception {
        return ResponseEntity.ok(ApiResponse.ok(accountService.signUp(signUpRequest)));
    }

    @PutMapping("/change-password")
    public ResponseEntity<ApiResponse<Void>> changePassword(@RequestBody @Valid ChangePasswordRequest changePasswordRequest, Authentication authentication) {
        if (!changePasswordRequest.getNewPassword().equals(changePasswordRequest.getConfirmPassword())) {
            return ResponseEntity.badRequest().body(new ApiResponse<>("1004", "Parameter value is invalid", null));
        } else {
//            TaiKhoan account = accountDetails.getTaiKhoan();
            Long accountId = Long.parseLong(authentication.getName());
            accountService.changePassword(accountId, changePasswordRequest.getOldPassword(), changePasswordRequest.getNewPassword());
            return ResponseEntity.ok(ApiResponse.ok(null));
        }
    }


    @ExceptionHandler(exception = ValidationException.class)
    public ResponseEntity<ApiResponse<Void>> invalidArguments() {
        return ResponseEntity.badRequest().body(new ApiResponse<>("1002", "Parameter is not enough", null));
    }

    @ExceptionHandler(exception = UserExistsException.class)
    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ApiResponse<Void> userExists() {
        return new ApiResponse<>("9996", "User existed", null);
    }

    @ExceptionHandler(exception = PasswordMismatchException.class)
    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ApiResponse<Void> passwordMismatch() {
        return new ApiResponse<>("3009", "Old password incorrect", null);
    }


}
