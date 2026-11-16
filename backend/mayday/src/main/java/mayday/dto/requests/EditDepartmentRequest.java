package mayday.dto.requests;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Size;

/** Du lieu sua khoa; field null giu gia tri cu, description rong xoa mo ta. */
public record EditDepartmentRequest(
        @Size(max = 150) String name,
        @Size(max = 1000) String description,
        @JsonProperty("branch_id") @JsonAlias("branchId") Long branchId) {
}
