package mayday.dtos.requests;

import jakarta.validation.constraints.Size;

public record UpdateBranchRequest(
        @Size(max = 150) String name,
        String address,
        @Size(max = 20) String phone,
        Boolean active) {
}
