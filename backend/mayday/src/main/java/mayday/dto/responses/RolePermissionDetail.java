package mayday.dto.responses;

import lombok.Getter;
import mayday.entities.nguoi.VaiTroQuyenHan;
import tools.jackson.databind.PropertyNamingStrategies;
import tools.jackson.databind.annotation.JsonNaming;

import java.time.OffsetDateTime;

@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
@Getter
public class RolePermissionDetail {
    private final Long permissionId;
    private final String name;
    private final String description;
    private final OffsetDateTime assignedAt;

    public RolePermissionDetail(VaiTroQuyenHan rolePerm) {
        permissionId = rolePerm.getId().getMaQuyenHan();
        name = rolePerm.getPermission().getTenQuyenHan();
        description = rolePerm.getPermission().getMoTa();
        assignedAt = rolePerm.getThoiDiemGan();
    }
}
