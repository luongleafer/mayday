package mayday.controllers;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import mayday.dto.requests.PermissionDataRequest;
import mayday.dto.responses.ApiResponse;
import mayday.dto.responses.PermissionCreationResponse;
import mayday.dto.responses.PermissionDetailResponse;
import mayday.entities.nguoi.QuyenHan;
import mayday.services.QuyenHanService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping({"api/permissions", "api/v1/permissions"})
public class PermissionController {
    private final QuyenHanService quyenHanService;

    public PermissionController(QuyenHanService quyenHanService) {
        this.quyenHanService = quyenHanService;
    }

    @PostMapping
    public ApiResponse<PermissionCreationResponse> setPermission(@Valid @RequestBody PermissionDataRequest request) {
        QuyenHan newPermission = quyenHanService.createPermission(request.getName(), request.getDescription());
        return ApiResponse.ok(new PermissionCreationResponse(newPermission));
    }

    @GetMapping
    public ApiResponse<List<PermissionDetailResponse>> getListPermissions() {
        return ApiResponse.ok(quyenHanService.getAllPermissions()
                                      .stream().map(PermissionDetailResponse::new)
                                      .toList()
        );
    }

    @PutMapping("/{id}")
    public ApiResponse<PermissionDetailResponse> editPermission(@PathVariable @Positive Long id,
                                                                @Valid @RequestBody PermissionDataRequest request) {
        return ApiResponse.ok(
                new PermissionDetailResponse(
                        quyenHanService.editPermission(
                                id,
                                request.getName(),
                                request.getDescription()
                        )
                )
        );
    }

    @DeleteMapping("/{id}")
    public ApiResponse<Void> deletePermission(@PathVariable @Positive Long id) {
        quyenHanService.deletePermission(id);
        return ApiResponse.ok(null);
    }

}
