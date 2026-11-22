package mayday.dto.responses;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import mayday.entities.nguoi.TaiKhoan;
import tools.jackson.databind.PropertyNamingStrategies;
import tools.jackson.databind.annotation.JsonNaming;

@Getter
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class UserDetailResponse {

    private final Long userId;
    private final String fullName;
    private final String phone;
    private final Long roleId;
    @JsonProperty("is_active")
    private final boolean isActive;

    public UserDetailResponse(TaiKhoan taiKhoan) {
        userId = taiKhoan.getMaTaiKhoan();
        fullName = taiKhoan.getNguoi().getHoTen();
        phone = taiKhoan.getTenDangNhap();
        roleId = taiKhoan.getVaiTro().getMaVaiTro();
        isActive = taiKhoan.getTrangThaiHoatDong();
    }

}
