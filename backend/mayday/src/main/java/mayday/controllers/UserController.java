package mayday.controllers;

import jakarta.validation.Valid;
import mayday.dto.requests.UserAssignmentRequest;
import mayday.dto.requests.UserCreationRequest;
import mayday.dto.requests.UserEditRequest;
import mayday.dto.responses.ApiResponse;
import mayday.dto.responses.ListUsersResponse;
import mayday.dto.responses.UserAssignmentResponse;
import mayday.dto.responses.UserDetailResponse;
import mayday.services.AccountService;
import mayday.services.UserService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping({"/api/admin/users", "/api/v1/admin/users"})
public class UserController {

    private final UserService userService;
    private final AccountService accountService;

    public UserController(UserService userService, AccountService accountService) {
        this.userService = userService;
        this.accountService = accountService;
    }


    @ResponseBody
    @GetMapping
    public ListUsersResponse getListUsers() {
        var usersList = userService.getListUsers();
        return new ListUsersResponse("1000", "OK", usersList, usersList.size());
    }

    @ResponseBody
    @GetMapping("/{id}")
    public ApiResponse<UserDetailResponse> getUserDetails(@PathVariable Long id) {
        return ApiResponse.ok(userService.getUserDetails(id));
    }

    @ResponseBody
    @PostMapping
    public ApiResponse<UserDetailResponse> createUser(@Valid @RequestBody UserCreationRequest request) {
        return ApiResponse.ok(userService.createUser(request));
    }

    @PutMapping("/{id}")
    @ResponseBody
    public ApiResponse<UserDetailResponse> editUser(@PathVariable("id") Long userId, @Valid @RequestBody UserEditRequest request) {
        return ApiResponse.ok(userService.editUser(userId, request));
    }

    @DeleteMapping("/{id}")
    @ResponseBody
    public ApiResponse<Void> deleteUser(@PathVariable("id") Long userId) {
        userService.deleteUser(userId);
        return ApiResponse.ok(null);
    }

    @PutMapping("/{id}/assign")
    @ResponseBody
    public ApiResponse<UserAssignmentResponse> assignUser(
            @PathVariable("id") Long userId,
            @RequestBody UserAssignmentRequest userAssignmentRequest
    ) {
        return ApiResponse.ok(accountService.assignStaff(userId, userAssignmentRequest));
    }


}
