package mayday.security;

import jakarta.annotation.Nonnull;
import mayday.entities.nguoi.TaiKhoan;
import mayday.repositories.TaiKhoanRepository;
import org.jspecify.annotations.Nullable;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Component
public class TaiKhoanManager implements UserDetailsManager {

    private final TaiKhoanRepository repository;

    public TaiKhoanManager(TaiKhoanRepository repository) {
        this.repository = repository;
    }

    @Override
    public void createUser(UserDetails user) {
        TaiKhoan taiKhoanMoi = new TaiKhoan();
        taiKhoanMoi.setTenDangNhap(user.getUsername());
        taiKhoanMoi.setMatKhauMaHoa(user.getPassword());
        repository.save(taiKhoanMoi);
    }

    @Override
    public void updateUser(UserDetails user) {
        TaiKhoan taiKhoan = repository.findByTenDangNhap(user.getUsername()).orElse(null);
        if (taiKhoan == null) return;
        taiKhoan.setMatKhauMaHoa(user.getPassword());
        taiKhoan.setTrangThaiHoatDong(user.isEnabled());
    }

    @Override
    public void deleteUser(String username) {
        repository.deleteTaiKhoanByTenDangNhap(username);

    }

    @Override
    public void changePassword(@Nullable String oldPassword, @Nullable String newPassword) {

    }

    @Override
    public boolean userExists(@Nonnull String username) {
        return repository.existsByTenDangNhap(username);
    }

    @Override
    @Nonnull
    public UserDetails loadUserByUsername(@Nonnull String username) throws UsernameNotFoundException {
        TaiKhoan taiKhoan = repository.findByTenDangNhap(username).orElseThrow(() -> new UsernameNotFoundException("khong tim thay tai khoan"));
        return User.builder().username(taiKhoan.getTenDangNhap()).password(taiKhoan.getMatKhauMaHoa()).build();
    }
}
