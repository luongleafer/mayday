package mayday.security;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import mayday.dto.responses.ApiResponse;
import mayday.dto.responses.LoginSuccessResponse;
import mayday.entities.nguoi.TaiKhoan;
import mayday.services.JwtService;
import org.jspecify.annotations.NonNull;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import tools.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.nio.charset.Charset;

public class LoginSuccessHandler implements AuthenticationSuccessHandler {

    private final JwtService jwtService;

    public LoginSuccessHandler(JwtService jwtService) {
        this.jwtService = jwtService;
    }

    @Override
    public void onAuthenticationSuccess(@NonNull HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        response.setStatus(200);
        response.setCharacterEncoding(Charset.defaultCharset());
        response.setContentType("application/json");
        var writer = response.getWriter();
        ObjectMapper objectMapper = new ObjectMapper();
        AccountDetails accountDetails = (AccountDetails) authentication.getPrincipal();
        if (accountDetails == null) return;
        TaiKhoan taiKhoan = accountDetails.getTaiKhoan();
        if (taiKhoan == null) {
            return;
        }
        objectMapper.writeValue(writer, ApiResponse.ok(new LoginSuccessResponse(taiKhoan, jwtService.generateJwtForAccount(accountDetails))));
        response.flushBuffer();
    }
}
