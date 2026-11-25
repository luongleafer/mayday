package mayday.security;

import jakarta.annotation.Nonnull;
import lombok.Getter;
import mayday.entities.nguoi.TaiKhoan;
import org.jspecify.annotations.Nullable;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

public class AccountDetails implements UserDetails {

    @Getter
    private final TaiKhoan taiKhoan;

    public AccountDetails(TaiKhoan taiKhoan) {
        this.taiKhoan = taiKhoan;
    }

    @Override
    @Nonnull
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of();
    }

    @Override
    @Nullable
    public String getPassword() {
        return taiKhoan.getMatKhauMaHoa();
    }

    @Override
    @Nonnull
    public String getUsername() {
        return taiKhoan.getTenDangNhap();
    }

}
