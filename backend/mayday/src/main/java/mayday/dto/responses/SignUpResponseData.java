package mayday.dto.responses;

import com.fasterxml.jackson.annotation.JsonProperty;
import mayday.entities.nguoi.Nguoi;
import mayday.entities.nguoi.TaiKhoan;

import java.time.OffsetDateTime;

public class SignUpResponseData {

    @JsonProperty("user_id")
    private Long userId;

    @JsonProperty("full_name")
    private String fullName;

    private String phone;

    private String gender;

    @JsonProperty("role_id")
    private Long roleId;

    @JsonProperty("is_active")
    private Boolean isActive;

    @JsonProperty("created_at")
    private OffsetDateTime createdAt;

    public SignUpResponseData(Nguoi person, TaiKhoan account) {
        this.userId = person.getMaNguoi();
        this.fullName = person.getHoTen();
        this.phone = person.getSoDienThoai();
        this.gender = person.getGioiTinh() == null ? "" : person.getGioiTinh().toString();
        this.roleId = account.getVaiTro().getMaVaiTro();
        this.isActive = account.getTrangThaiHoatDong();
        this.createdAt = account.getThoiDiemTao();
    }

}
