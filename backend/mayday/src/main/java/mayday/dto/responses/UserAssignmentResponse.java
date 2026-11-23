package mayday.dto.responses;

import com.fasterxml.jackson.annotation.JsonUnwrapped;
import lombok.AllArgsConstructor;
import lombok.Getter;
import mayday.dto.requests.UserAssignmentRequest;

@Getter
@AllArgsConstructor
public class UserAssignmentResponse {

    private Long userId;

    @JsonUnwrapped
    private UserAssignmentRequest request;


}
