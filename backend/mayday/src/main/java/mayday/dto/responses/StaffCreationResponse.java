package mayday.dto.responses;


import com.fasterxml.jackson.annotation.JsonProperty;
import mayday.dto.requests.StaffCreationRequest;

public record StaffCreationResponse(
        @JsonProperty("user_id")
        Long userId,

        @JsonProperty("full_name")
        String fullName,

        @JsonProperty("role_id")
        Long roleId,

        @JsonProperty("branch_id")
        Long branchId,

        @JsonProperty("department_id")
        Long departmentId
) {

    public StaffCreationResponse(Long newUserId, StaffCreationRequest request) {
        this(newUserId, request.getFullName(), request.getRoleId(), request.getBranchId(), request.getDepartmentId());
    }


}
