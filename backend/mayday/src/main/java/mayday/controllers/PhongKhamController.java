package mayday.controllers;

import jakarta.validation.Valid;
import java.util.List;
import java.util.Map;
import mayday.dtos.ApiResponse;
import mayday.dtos.requests.CreateRoomRequest;
import mayday.dtos.requests.UpdateRoomRequest;
import mayday.dtos.responses.RoomResponse;
import mayday.services.PhongKhamService;
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
@RequestMapping("/api/v1/rooms")
public class PhongKhamController {
    private final PhongKhamService service;

    public PhongKhamController(PhongKhamService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<ApiResponse<RoomResponse>> create(@Valid @RequestBody CreateRoomRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(ApiResponse.created(service.create(request)));
    }

    @GetMapping
    public ApiResponse<List<RoomResponse>> findAll(
            @RequestParam(name = "department_id", required = false) Long departmentId) {
        return ApiResponse.ok(service.findAll(departmentId));
    }

    @GetMapping("/{id}")
    public ApiResponse<RoomResponse> findById(@PathVariable Long id) {
        return ApiResponse.ok(service.findById(id));
    }

    @PutMapping("/{id}")
    public ApiResponse<RoomResponse> update(@PathVariable Long id,
                                              @Valid @RequestBody UpdateRoomRequest request) {
        return ApiResponse.ok(service.update(id, request));
    }

    @DeleteMapping("/{id}")
    public ApiResponse<Map<String, Object>> delete(@PathVariable Long id) {
        return ApiResponse.ok(service.delete(id));
    }
}
