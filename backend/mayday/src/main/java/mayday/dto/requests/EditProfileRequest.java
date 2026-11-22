package mayday.dto.requests;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Positive;
import tools.jackson.databind.PropertyNamingStrategies;
import tools.jackson.databind.annotation.JsonNaming;

@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public record EditProfileRequest(
        String fullName,
        String gender,
        @Positive
        Integer yearOfBirth,
        String career,
        String ethnic,
        String hamletAddress,
        String communeAddress,
        String cityAddress,
        String workPlace,
        String healthInsuranceNumber,
        String healthInsuranceValidate,
        String nextOfKinName,
        String nextOfKinPhone
) {
}
