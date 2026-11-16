package mayday.controllers.cosovatchat;

import jakarta.validation.Valid;
import java.util.List;
import java.util.Map;
import mayday.dto.responses.ApiResponse;
import mayday.dto.requests.SetDepartmentRequest;
import mayday.dto.requests.EditDepartmentRequest;
import mayday.dto.responses.DepartmentResponse;
import mayday.services.cosovatchat.KhoaPhongService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/** Cung cap API quan ly khoa; branch_id la bo loc tuy chon khi lay danh sach. */
@RestController
@RequestMapping({"/api/v1/departments", "/api/departments"})
public class KhoaPhongController {
    private final KhoaPhongService service;

    public KhoaPhongController(KhoaPhongService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<ApiResponse<DepartmentResponse>> setDepartment(
            @Valid @RequestBody SetDepartmentRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(ApiResponse.created(service.setDepartment(request)));
    }

    @GetMapping
    public ApiResponse<List<DepartmentResponse>> getListDepartments(
            @RequestParam(name = "branch_id", required = false) Long branchId) {
        return ApiResponse.ok(service.getListDepartments(branchId));
    }

    @GetMapping("/{id}")
    public ApiResponse<DepartmentResponse> getDepartmentDetail(@PathVariable Long id) {
        return ApiResponse.ok(service.getDepartmentDetail(id));
    }

    @PutMapping("/{id}")
    public ApiResponse<DepartmentResponse> editDepartment(@PathVariable Long id,
                                                    @Valid @RequestBody EditDepartmentRequest request) {
        return ApiResponse.ok(service.editDepartment(id, request));
    }

    @DeleteMapping("/{id}")
    public ApiResponse<Map<String, Object>> deleteDepartment(@PathVariable Long id) {
        service.deleteDepartment(id);
        return ApiResponse.ok(Map.of());
    }
}
