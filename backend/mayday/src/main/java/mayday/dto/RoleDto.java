package mayday.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import mayday.entities.nguoi.VaiTro;
import tools.jackson.databind.PropertyNamingStrategies;
import tools.jackson.databind.annotation.JsonNaming;

@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
@Setter(AccessLevel.PUBLIC)
@Getter
@NoArgsConstructor
public class RoleDto {
    private Long id;

    @NotNull
    private String name;

    private String description;
    private Long parentRoleId;

    public RoleDto(VaiTro role) {
        this.id = role.getMaVaiTro();
        this.name = role.getTenVaiTro();
        this.description = role.getMoTaVaiTro();
        this.parentRoleId = role.getMaVaiTroCha();
    }


}
