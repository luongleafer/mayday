package mayday.dto.responses;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.time.OffsetDateTime;

/**
 * Du lieu phong tra ve client theo dac ta Google Sheets:
 * - set_room / get_list_rooms: id, name, department_id, description, created_at
 * - get_room_detail / edit_room: id, name, description, department_id, created_at, updated_at
 */
public record RoomResponse(
        Long id,
        String name,
        String description,
        @JsonProperty("department_id") Long departmentId,
        @JsonProperty("created_at") @JsonInclude(JsonInclude.Include.NON_NULL) OffsetDateTime createdAt,
        @JsonProperty("updated_at") @JsonInclude(JsonInclude.Include.NON_NULL) OffsetDateTime updatedAt) {
    public RoomResponse(Long id, String name, Long departmentId) {
        this(id, name, null, departmentId, null, null);
    }

    public RoomResponse(Long id, String name, String description, Long departmentId, OffsetDateTime createdAt) {
        this(id, name, description, departmentId, createdAt, null);
    }
}
