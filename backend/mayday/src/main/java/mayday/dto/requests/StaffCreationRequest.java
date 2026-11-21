package mayday.dto.requests;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.annotation.Nullable;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Getter;
import mayday.entities.nguoi.Gender;

@AllArgsConstructor
@Getter
public class StaffCreationRequest extends SignUpRequest {

    @JsonProperty("role_id")
    @NotNull
    @Positive
    private Long roleId;

    @JsonProperty("branch_id")
    @NotNull
    @Positive
    private Long branchId;

    @JsonProperty("department_id")
    @NotNull
    @Positive
    private Long departmentId;

    @JsonProperty("room_id")
    @Positive
    private Long roomId;

}
