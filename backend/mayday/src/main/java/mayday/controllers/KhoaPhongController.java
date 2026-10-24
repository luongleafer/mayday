package mayday.controllers;

import jakarta.validation.Valid;
import java.util.List;
import java.util.Map;
import mayday.dtos.ApiResponse;
import mayday.dtos.requests.CreateDepartmentRequest;
import mayday.dtos.requests.UpdateDepartmentRequest;
import mayday.dtos.responses.DepartmentResponse;
import mayday.services.KhoaPhongService;
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

@RestController
@RequestMapping("/api/v1/departments")
public class KhoaPhongController {
    private final KhoaPhongService service;

    public KhoaPhongController(KhoaPhongService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<ApiResponse<DepartmentResponse>> create(
            @Valid @RequestBody CreateDepartmentRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(ApiResponse.created(service.create(request)));
    }

    @GetMapping
    public ApiResponse<List<DepartmentResponse>> findAll(
            @RequestParam(name = "branch_id", required = false) Long branchId) {
        return ApiResponse.ok(service.findAll(branchId));
    }

    @GetMapping("/{id}")
    public ApiResponse<DepartmentResponse> findById(@PathVariable Long id) {
        return ApiResponse.ok(service.findById(id));
    }

    @PutMapping("/{id}")
    public ApiResponse<DepartmentResponse> update(@PathVariable Long id,
                                                    @Valid @RequestBody UpdateDepartmentRequest request) {
        return ApiResponse.ok(service.update(id, request));
    }

    @DeleteMapping("/{id}")
    public ApiResponse<Map<String, Object>> delete(@PathVariable Long id) {
        return ApiResponse.ok(service.delete(id));
    }
}
