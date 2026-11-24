package mayday.security;

import jakarta.annotation.Nonnull;
import mayday.entities.nguoi.TaiKhoan;
import mayday.repositories.TaiKhoanRepository;
import org.jspecify.annotations.NonNull;
import org.jspecify.annotations.Nullable;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.stereotype.Component;

@Component
public class AccountManager implements UserDetailsManager {

    private final TaiKhoanRepository repository;

    public AccountManager(TaiKhoanRepository repository) {
        this.repository = repository;
    }

    @Override
    public void createUser(UserDetails user) {
        TaiKhoan newAccount = new TaiKhoan();
        newAccount.setTenDangNhap(user.getUsername());
        newAccount.setMatKhauMaHoa(user.getPassword());
        repository.save(newAccount);
    }

    @Override
    public void updateUser(UserDetails user) {
        TaiKhoan account = repository.findByTenDangNhap(user.getUsername()).orElse(null);
        if (account == null) return;
        account.setMatKhauMaHoa(user.getPassword());
        account.setTrangThaiHoatDong(user.isEnabled());
        repository.save(account);
    }

    @Override
    public void deleteUser(@NonNull String username) {
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
        TaiKhoan taiKhoan = repository.findWithPersonByTenDangNhap(username).orElseThrow(() -> new UsernameNotFoundException("khong tim thay tai khoan"));
//        return User.builder().username(taiKhoan.getTenDangNhap()).password(taiKhoan.getMatKhauMaHoa()).build();
        return new AccountDetails(taiKhoan);
    }
}
