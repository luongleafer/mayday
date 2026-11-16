package mayday.dto.requests;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

/** Du lieu tao khoa theo dac ta API (name, description, branch_id). */
public record SetDepartmentRequest(
        @NotBlank @Size(max = 150) String name,
        @Size(max = 1000) String description,
        @NotNull @JsonProperty("branch_id") @JsonAlias("branchId") Long branchId) {
}
