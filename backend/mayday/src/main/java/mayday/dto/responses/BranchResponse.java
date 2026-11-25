package mayday.dto.responses;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.time.OffsetDateTime;

/**
 * Du lieu chi nhanh tra ve client theo dac ta Google Sheets:
 * - get_list_branches: id, name, address, phone, is_active
 * - set_branch / get_branch_detail / edit_branch: id, name, address, phone, created_at, updated_at
 */
public record BranchResponse(
        Long id,
        String name,
        String address,
        String phone,
        @JsonProperty("is_active") @JsonInclude(JsonInclude.Include.NON_NULL) Boolean active,
        @JsonProperty("created_at") @JsonInclude(JsonInclude.Include.NON_NULL) OffsetDateTime createdAt,
        @JsonProperty("updated_at") @JsonInclude(JsonInclude.Include.NON_NULL) OffsetDateTime updatedAt) {
    public BranchResponse(Long id, String name, String address, String phone, Boolean active) {
        this(id, name, address, phone, active, null, null);
    }

    public BranchResponse(Long id, String name, String address, String phone, OffsetDateTime createdAt, OffsetDateTime updatedAt) {
        this(id, name, address, phone, null, createdAt, updatedAt);
    }
}
