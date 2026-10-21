package mayday.dtos.requests;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record CreateDepartmentRequest(
        @NotBlank @Size(max = 150) String name,
        @NotBlank @Size(max = 50) String type,
        String description,
        @NotNull Long branchId) {
}
