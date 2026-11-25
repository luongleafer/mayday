package mayday.dto.requests;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Size;

/** Du lieu sua phong; chi cap nhat cac field khac null, description rong xoa mo ta. */
public record EditRoomRequest(
        @Size(max = 150) String name,
        @Size(max = 1000) String description,
        @JsonProperty("department_id") @JsonAlias("departmentId") Long departmentId) {
    public EditRoomRequest(String name, Long departmentId) {
        this(name, null, departmentId);
    }
}
