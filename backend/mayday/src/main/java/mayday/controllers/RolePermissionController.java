package mayday.controllers;

import jakarta.validation.Valid;
import mayday.dto.requests.SetRolePermissionsRequest;
import mayday.dto.responses.ApiResponse;
import mayday.dto.responses.RolePermissionDetail;
import mayday.entities.nguoi.VaiTro;
import mayday.exceptions.NotFoundException;
import mayday.services.VaiTroQuyenHanService;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping({"/api/role_permissions", "api/v1/role_permissions"})
public class RolePermissionController {

    private final VaiTroQuyenHanService vaiTroQuyenHanService;

    public RolePermissionController(VaiTroQuyenHanService vaiTroQuyenHanService) {
        this.vaiTroQuyenHanService = vaiTroQuyenHanService;
    }

    @PostMapping
    @ResponseBody
    public ApiResponse<Void> setRolePermissions(@Valid @RequestBody SetRolePermissionsRequest request, Authentication authentication) {
        Long assigneeId = Long.parseLong(authentication.getName());
        vaiTroQuyenHanService.addPermissionToRole(request.getRoleId(), request.getPermissions(), assigneeId);
        return ApiResponse.ok(null);
    }

    @GetMapping("/{roleId}")
    @ResponseBody
    public ApiResponse<List<RolePermissionDetail>> getRolePermissions(@PathVariable Long roleId) {
        var allPermissions =
                vaiTroQuyenHanService.getRolePermissions(roleId);
        return ApiResponse.ok(allPermissions
        );
    }


    @DeleteMapping("/{roleId}/{permissionId}")
    @ResponseBody
    public ApiResponse<Void> deleteRolePermissions(@PathVariable Long roleId, @PathVariable Long permissionId) {
        vaiTroQuyenHanService.deleteRolePermission(roleId, permissionId);
        return ApiResponse.ok(null);
    }

}
