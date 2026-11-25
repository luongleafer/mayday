package mayday.dto.responses;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class ListUsersResponse {

    private String code;
    private String message;
    private List<UserDetailResponse> data;
    private int total;
}
