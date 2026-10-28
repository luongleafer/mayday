package mayday.dto.requests;

import jakarta.validation.constraints.Size;

/** Du lieu sua phong; chi cap nhat cac field khac null. */
public record EditRoomRequest(
        @Size(max = 150) String name,
        Long departmentId) {
}
