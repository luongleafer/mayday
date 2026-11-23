package mayday.dto.requests;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Getter;
import tools.jackson.databind.PropertyNamingStrategies;
import tools.jackson.databind.annotation.JsonNaming;

@Getter
@AllArgsConstructor
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class UserAssignmentRequest {
    @NotNull
    @Positive
    private Long branchId;

    @NotNull
    @Positive
    private Long departmentId;

    @Positive
    private Long roomId;
}
