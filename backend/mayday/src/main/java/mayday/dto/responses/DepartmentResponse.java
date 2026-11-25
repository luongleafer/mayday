package mayday.dto.responses;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.time.OffsetDateTime;

/**
 * Du lieu khoa tra ve client theo dac ta Google Sheets:
 * - get_list_departments: id, name, branch_id, description
 * - set_department: id, name, branch_id, description, created_at
 * - get_department_detail / edit_department: id, name, description, branch_id, created_at, updated_at
 */
public record DepartmentResponse(
        Long id,
        String name,
        String description,
        @JsonProperty("branch_id") Long branchId,
        @JsonProperty("created_at") @JsonInclude(JsonInclude.Include.NON_NULL) OffsetDateTime createdAt,
        @JsonProperty("updated_at") @JsonInclude(JsonInclude.Include.NON_NULL) OffsetDateTime updatedAt) {
    public DepartmentResponse(Long id, String name, String description, Long branchId) {
        this(id, name, description, branchId, null, null);
    }

    public DepartmentResponse(Long id, String name, String description, Long branchId, OffsetDateTime createdAt) {
        this(id, name, description, branchId, createdAt, null);
    }
}
