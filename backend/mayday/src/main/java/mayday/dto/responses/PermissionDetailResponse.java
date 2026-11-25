package mayday.dto.responses;

import lombok.Getter;
import mayday.entities.nguoi.QuyenHan;
import tools.jackson.databind.PropertyNamingStrategies;
import tools.jackson.databind.annotation.JsonNaming;

import java.time.OffsetDateTime;

@Getter
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class PermissionDetailResponse {
    private final Long id;
    private final String name;
    private final String description;
    private final OffsetDateTime createdAt;
    private final OffsetDateTime updatedAt;

    public PermissionDetailResponse(QuyenHan permission) {
        id = permission.getMaQuyenHan();
        name = permission.getTenQuyenHan();
        description = permission.getMoTa();
        createdAt = permission.getThoiDiemTao();
        updatedAt = permission.getThoiDiemCapNhat();
    }
}
