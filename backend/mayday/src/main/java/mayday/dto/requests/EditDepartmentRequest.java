package mayday.dto.requests;

import jakarta.validation.constraints.Size;

/** Du lieu sua khoa; field null giu gia tri cu, description rong xoa mo ta. */
public record EditDepartmentRequest(
        @Size(max = 150) String name,
        @Size(max = 50) String type,
        String description,
        Long branchId) {
}
