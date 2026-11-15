package mayday.dto.requests;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class ChangePasswordRequest {

    @JsonProperty("old_password")
    @NotNull
    private String oldPassword;


    @JsonProperty("new_password")
    @NotNull
    private String newPassword;

    @JsonProperty("confirm_password")
    @NotNull
    private String confirmPassword;
}
