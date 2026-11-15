package mayday.dto.responses;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import mayday.entities.nguoi.Nguoi;
import mayday.entities.nguoi.TaiKhoan;
import org.springframework.transaction.annotation.Transactional;

@Getter
public class LoginSuccessResponse {

    @JsonProperty("token")
    private String jwtToken;


    static class UserInfo {
        @JsonProperty("user_id")
        String personId;

        @JsonProperty("full_name")
        String fullName;
        String phone;

        @JsonProperty("role_id")
        String role;
    }

    @JsonProperty("user_info")
    private UserInfo userInfo;

    public LoginSuccessResponse(TaiKhoan taiKhoan, String jwtToken) {
        userInfo = new UserInfo();
        Nguoi nguoi = taiKhoan.getNguoi();
        userInfo.personId = nguoi.getMaNguoi().toString();
        userInfo.fullName = nguoi.getHoTen();
        userInfo.phone = nguoi.getSoDienThoai();
        userInfo.role = taiKhoan.getVaiTro().getMaVaiTro().toString();
        this.jwtToken = jwtToken;
    }


}
