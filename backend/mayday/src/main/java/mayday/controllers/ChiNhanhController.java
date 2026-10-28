package mayday.controllers;

import jakarta.validation.Valid;
import java.util.List;
import java.util.Map;
import mayday.dto.requests.SetBranchRequest;
import mayday.dto.requests.EditBranchRequest;
import mayday.dto.responses.BranchResponse;
import mayday.dto.responses.ApiResponse;
import mayday.services.ChiNhanhService;
import org.springframework.web.bind.annotation.*;

/** Nhan request quan ly chi nhanh; giao xu ly nghiep vu cho service va dong goi response. */
@RestController
@RequestMapping("/api/v1/branches")
public class ChiNhanhController {
    private final ChiNhanhService service;

    public ChiNhanhController(ChiNhanhService service) {
        this.service = service;
    }

    @PostMapping
    public ApiResponse<BranchResponse> setBranch(@Valid @RequestBody SetBranchRequest request) {
        return ApiResponse.created(service.setBranch(request));
    }

    @GetMapping
    public ApiResponse<List<BranchResponse>> getListBranches() {
        return ApiResponse.ok(service.getListBranches());
    }

    @GetMapping("/{id}")
    public ApiResponse<BranchResponse> getBranchDetail(@PathVariable Long id) {
        return ApiResponse.ok(service.getBranchDetail(id));
    }

    @PutMapping("/{id}")
    public ApiResponse<BranchResponse> editBranch(@PathVariable Long id,
            @Valid @RequestBody EditBranchRequest request) {
        return ApiResponse.ok(service.editBranch(id, request));
    }

    @DeleteMapping("/{id}")
    public ApiResponse<Map<String, Object>> deleteBranch(@PathVariable Long id) {
        service.deleteBranch(id);
        return ApiResponse.ok(Map.of());
    }
}
