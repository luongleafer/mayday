package mayday.controllers;

import jakarta.validation.Valid;
import jakarta.validation.ValidationException;
import mayday.dto.requests.ChangePasswordRequest;
import mayday.dto.requests.SignUpRequest;
import mayday.dto.requests.StaffCreationRequest;
import mayday.dto.responses.ApiResponse;
import mayday.dto.responses.SignUpResponseData;
import mayday.dto.responses.StaffCreationResponse;
import mayday.entities.nguoi.TaiKhoan;
import mayday.exceptions.PasswordMismatchException;
import mayday.exceptions.UserExistsException;
import mayday.services.AccountService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
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
        TaiKhoan newAccount = accountService.signUp(signUpRequest, null);
        return ResponseEntity.ok(ApiResponse.ok(new SignUpResponseData(newAccount.getNguoi(), newAccount)));
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

    @PostMapping("/admin/create-staff")
    public ResponseEntity<ApiResponse<StaffCreationResponse>> createStaff(@Valid @RequestBody StaffCreationRequest staffCreationRequest) throws Exception {
        TaiKhoan newAccount = accountService.createStaff(staffCreationRequest);
        return ResponseEntity.ok(ApiResponse.ok(new StaffCreationResponse(newAccount.getMaTaiKhoan(), staffCreationRequest)));
    }


    @ExceptionHandler(exception = ValidationException.class)
    public ResponseEntity<ApiResponse<Void>> insufficientArguments() {
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
        return new ApiResponse<>("9995", "User is not validated", null);
    }


}
