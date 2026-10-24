package mayday.dtos.requests;

import jakarta.validation.constraints.Size;

public record UpdateDepartmentRequest(
        @Size(max = 150) String name,
        @Size(max = 50) String type,
        String description,
        Long branchId) {
}
