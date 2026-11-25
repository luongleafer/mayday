package mayday.dto.requests;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

/** Du lieu tao phong; department_id phai tham chieu khoa dang ton tai. */
public record SetRoomRequest(
        @NotBlank @Size(max = 150) String name,
        @Size(max = 1000) String description,
        @NotNull @JsonProperty("department_id") @JsonAlias("departmentId") Long departmentId) {
    public SetRoomRequest(String name, Long departmentId) {
        this(name, null, departmentId);
    }
}
