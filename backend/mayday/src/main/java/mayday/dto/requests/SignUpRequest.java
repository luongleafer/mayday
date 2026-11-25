package mayday.dto.requests;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import mayday.entities.nguoi.Gender;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SignUpRequest {

    @JsonProperty("full_name")
    @NotNull
    private String fullName;

    @NotNull
    private String phone;

    @NotNull
    private String password;

    private Gender gender;
}
