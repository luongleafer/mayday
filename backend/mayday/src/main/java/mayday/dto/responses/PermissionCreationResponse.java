package mayday.dto.responses;


import lombok.Getter;
import mayday.entities.nguoi.QuyenHan;

@Getter
public class PermissionCreationResponse {
    private final Long id;
    private final String name;
    private final String description;

    public PermissionCreationResponse(QuyenHan permission) {
        id = permission.getMaQuyenHan();
        name = permission.getTenQuyenHan();
        description = permission.getMoTa();
    }
}
