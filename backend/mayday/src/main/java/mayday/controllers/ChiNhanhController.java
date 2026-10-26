package mayday.controllers;

import mayday.dtos.requests.SetBranchRequest;
import mayday.dtos.requests.EditBranchRequest;
import mayday.dtos.responses.ApiResponse;
import mayday.dtos.responses.BranchResponse;
import mayday.services.ChiNhanhService;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/api/v1/branches")
public class ChiNhanhController {

    private final ChiNhanhService chiNhanhService;

    public ChiNhanhController(ChiNhanhService chiNhanhService) {
        this.chiNhanhService = chiNhanhService;
    }

 
    // set_branch (branch01)
    @PostMapping
    public ResponseEntity<ApiResponse<BranchResponse>> setBranch(@RequestBody SetBranchRequest req) {
        try {
            BranchResponse result = chiNhanhService.setBranch(req);
            return ResponseEntity.ok(new ApiResponse<>("1001", "Created successfully", result));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(new ApiResponse<>("2003", e.getMessage(), null));
        }
    }

    // get_list_branches (branch02)
    // Lấy danh sách tất cả các chi nhánh
    @GetMapping
    public ResponseEntity<ApiResponse<List<BranchResponse>>> getListBranches() {
        List<BranchResponse> list = chiNhanhService.getListBranches();
        return ResponseEntity.ok(new ApiResponse<>("1000", "OK", list));
    }

    // get_branch_detail (branch03)
    // Lấy thông tin chi tiết của một chi nhánh theo ID
    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<BranchResponse>> getBranchDetail(@PathVariable Long id) {
        try {
            BranchResponse result = chiNhanhService.getBranchDetail(id);
            return ResponseEntity.ok(new ApiResponse<>("1000", "OK", result));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(new ApiResponse<>("2003", e.getMessage(), null));
        }
    }

    // edit_branch (branch04)
    // Cập nhật thông tin (tên, địa chỉ, SĐT) của một chi nhánh
    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<BranchResponse>> editBranch(
            @PathVariable Long id,
            @RequestBody EditBranchRequest req) {
        try {
            BranchResponse result = chiNhanhService.editBranch(id, req);
            return ResponseEntity.ok(new ApiResponse<>("1000", "OK", result));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(new ApiResponse<>("2003", e.getMessage(), null));
        }
    }

    // delete_branch (branch05)
    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Object>> deleteBranch(@PathVariable Long id) {
        try {
            chiNhanhService.deleteBranch(id);
            return ResponseEntity.ok(new ApiResponse<>("1000", "Xóa thành công", Collections.emptyMap()));
        } catch (DataIntegrityViolationException e) {
            // Test case: Bị FK RESTRICT chặn do tồn tại khoa/phòng hoặc nhân viên
            return ResponseEntity.badRequest().body(new ApiResponse<>(
                    "2003",
                    "Invalid parameter value: Không thể xóa do tồn tại dữ liệu con",
                    null
            ));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(new ApiResponse<>("2003", e.getMessage(), null));
        }
    }
}