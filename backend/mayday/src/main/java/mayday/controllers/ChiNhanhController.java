package mayday.controllers;

import jakarta.validation.Valid;
import java.util.List;
import java.util.Map;
import mayday.dtos.ApiResponse;
import mayday.dtos.requests.CreateBranchRequest;
import mayday.dtos.requests.UpdateBranchRequest;
import mayday.dtos.responses.BranchResponse;
import mayday.services.ChiNhanhService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/branches")
public class ChiNhanhController {
    private final ChiNhanhService service;

    public ChiNhanhController(ChiNhanhService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<ApiResponse<BranchResponse>> create(@Valid @RequestBody CreateBranchRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(ApiResponse.created(service.create(request)));
    }

    @GetMapping
    public ApiResponse<List<BranchResponse>> findAll() {
        return ApiResponse.ok(service.findAll());
    }

    @GetMapping("/{id}")
    public ApiResponse<BranchResponse> findById(@PathVariable Long id) {
        return ApiResponse.ok(service.findById(id));
    }

    @PutMapping("/{id}")
    public ApiResponse<BranchResponse> update(@PathVariable Long id,
                                                @Valid @RequestBody UpdateBranchRequest request) {
        return ApiResponse.ok(service.update(id, request));
    }

    @DeleteMapping("/{id}")
    public ApiResponse<Map<String, Object>> delete(@PathVariable Long id) {
        return ApiResponse.ok(service.delete(id));
    }
}
