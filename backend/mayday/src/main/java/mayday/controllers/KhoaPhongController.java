package mayday.controllers;

import mayday.dtos.requests.SetDepartmentRequest;
import mayday.dtos.requests.EditDepartmentRequest;
import mayday.dtos.responses.ApiResponse;
import mayday.dtos.responses.DepartmentResponse;
import mayday.services.KhoaPhongService;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/api/v1/departments")
public class KhoaPhongController {

    private final KhoaPhongService khoaPhongService;

    public KhoaPhongController(KhoaPhongService khoaPhongService) {
        this.khoaPhongService = khoaPhongService;
    }

    // dept01
    @PostMapping
    public ResponseEntity<ApiResponse<DepartmentResponse>> setDepartment(@RequestBody SetDepartmentRequest req) {
        try {
            DepartmentResponse result = khoaPhongService.setDepartment(req);
            return ResponseEntity.ok(new ApiResponse<>("1001", "Created successfully", result));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(new ApiResponse<>("2003", e.getMessage(), null));
        }
    }

    // dept02:
    @GetMapping
    public ResponseEntity<ApiResponse<List<DepartmentResponse>>> getListDepartments(@RequestParam(value = "branch_id", required = false) Long branchId) {
        if (branchId == null) {
            return ResponseEntity.badRequest().body(new ApiResponse<>("2003", "Thiếu tham số branch_id", null));
        }
        List<DepartmentResponse> list = khoaPhongService.getListDepartmentsByBranch(branchId);
        return ResponseEntity.ok(new ApiResponse<>("1000", "OK", list));
    }

    // dept03
    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<DepartmentResponse>> getDepartmentDetail(@PathVariable Long id) {
        try {
            DepartmentResponse result = (DepartmentResponse) khoaPhongService.getDepartmentDetail(id);
            return ResponseEntity.ok(new ApiResponse<>("1000", "OK", result));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(new ApiResponse<>("2003", e.getMessage(), null));
        }
    }
    // dept04
    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<DepartmentResponse>> editDepartment(
            @PathVariable Long id,
            @RequestBody EditDepartmentRequest req) {
        try {
            DepartmentResponse result = khoaPhongService.editDepartment(id, req);
            return ResponseEntity.ok(new ApiResponse<>("1000", "OK", result));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(new ApiResponse<>("2003", e.getMessage(), null));
        }
    }

    // dept05
    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Object>> deleteDepartment(@PathVariable Long id) {
        try {
            khoaPhongService.deleteDepartment(id);
            return ResponseEntity.ok(new ApiResponse<>("1000", "Xóa thành công", Collections.emptyMap()));
        } catch (DataIntegrityViolationException e) {
            return ResponseEntity.badRequest().body(new ApiResponse<>(
                    "2003",
                    "Invalid parameter value: Không thể xóa do tồn tại dữ liệu ràng buộc",
                    null
            ));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(new ApiResponse<>("2003", e.getMessage(), null));
        }
    }
}