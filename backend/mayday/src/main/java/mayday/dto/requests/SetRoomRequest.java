package mayday.dto.requests;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

/** Du lieu tao phong; departmentId phai tham chieu khoa dang ton tai. */
public record SetRoomRequest(
        @NotBlank @Size(max = 150) String name,
        @NotNull Long departmentId) {
}
