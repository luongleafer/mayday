package mayday.dto.requests;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

/** Du lieu tao khoa; type tuong ung cot loai_khoa bat buoc trong database. */
public record SetDepartmentRequest(
        @NotBlank @Size(max = 150) String name,
        @NotBlank @Size(max = 50) String type,
        String description,
        @NotNull Long branchId) {
}
