package mayday.dtos.requests;

import jakarta.validation.constraints.Size;

public record EditRoomRequest(
        @Size(max = 150) String name,
        Long departmentId) {
}
