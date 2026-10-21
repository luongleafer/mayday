package mayday.dtos.requests;

import jakarta.validation.constraints.Size;

public record UpdateRoomRequest(
        @Size(max = 150) String name,
        Long departmentId) {
}
