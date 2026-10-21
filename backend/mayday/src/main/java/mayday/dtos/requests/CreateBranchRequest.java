package mayday.dtos.requests;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record CreateBranchRequest(
        @NotBlank @Size(max = 150) String name,
        @NotBlank String address,
        @Size(max = 20) String phone) {
}
