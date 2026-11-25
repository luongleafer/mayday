package mayday.dto.requests;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Range;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class UserCreationRequest extends SignUpRequest {
    @NotNull
    @Positive
    @JsonProperty("role_id")
    private Long roleId;

    @Range(min = 0, max = 1)
    private Integer isActive;
}
