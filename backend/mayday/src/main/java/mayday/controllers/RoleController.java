package mayday.controllers;

import jakarta.validation.Valid;
import mayday.dto.RoleCreationRequest;
import mayday.dto.RoleDetailResponse;
import mayday.dto.RoleDto;
import mayday.dto.responses.ApiResponse;
import mayday.services.VaiTroService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping({"api/roles", "api/v1/roles"})
public class RoleController {

    private final VaiTroService vaiTroService;

    public RoleController(VaiTroService vaiTroService) {
        this.vaiTroService = vaiTroService;
    }

    @PostMapping
    public ApiResponse<RoleDto> setRole(@Valid @RequestBody RoleCreationRequest request) {
        return ApiResponse.ok(vaiTroService.createRole(request));
    }

    @GetMapping
    public ApiResponse<List<RoleDetailResponse>> getRole() {
        return ApiResponse.ok(vaiTroService.getAllRoles());
    }

}
