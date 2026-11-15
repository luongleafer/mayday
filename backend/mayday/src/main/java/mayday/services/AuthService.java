package mayday.services;

import mayday.dto.responses.ApiResponse;
import mayday.security.AccountManager;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    private final AccountManager accountManager;

    private final PasswordEncoder passwordEncoder;

    public AuthService(AccountManager accountManager, PasswordEncoder passwordEncoder) {
        this.accountManager = accountManager;
        this.passwordEncoder = passwordEncoder;
    }

    public ApiResponse<String> login(String username, String password) {
        if (!accountManager.userExists(username)) {
            return new ApiResponse<>("404", "User not found", "failed");
        }
        UserDetails details = accountManager.loadUserByUsername(username);
        String passwordHash = details.getPassword();
        if (!passwordEncoder.matches(password, passwordHash)) {
            return new ApiResponse<>("403", "Username or password not correct", "failed");
        }
        return ApiResponse.ok("success");
    }
}
