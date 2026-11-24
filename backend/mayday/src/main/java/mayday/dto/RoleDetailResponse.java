package mayday.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import mayday.entities.nguoi.VaiTro;

@JsonIgnoreProperties({"parent_role_id"})
public class RoleDetailResponse extends RoleDto {

    public RoleDetailResponse(VaiTro role) {
        super(role);
    }

}
