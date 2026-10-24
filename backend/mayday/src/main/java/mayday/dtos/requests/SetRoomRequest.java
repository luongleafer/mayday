package mayday.dtos.requests;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record SetRoomRequest(
        @NotBlank @Size(max = 150) String name,
        @NotNull Long departmentId) {
}
