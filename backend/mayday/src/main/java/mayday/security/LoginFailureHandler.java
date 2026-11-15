package mayday.security;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import mayday.dto.responses.ApiResponse;
import org.jspecify.annotations.NonNull;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import tools.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.nio.charset.Charset;

public class LoginFailureHandler implements AuthenticationFailureHandler {
    @Override
    public void onAuthenticationFailure(@NonNull HttpServletRequest request, @NonNull HttpServletResponse response, @NonNull AuthenticationException exception) throws IOException, ServletException {
        response.setCharacterEncoding(Charset.defaultCharset());
        response.setContentType("application/json");
        ObjectMapper objectMapper = new ObjectMapper();
        var writer = response.getWriter();
        if (exception instanceof UsernameNotFoundException) {
            response.setStatus(404);
            objectMapper.writeValue(writer, new ApiResponse<Void>("3007", "Username not found", null));
        } else if (exception instanceof BadCredentialsException) {
            response.setStatus(403);
            objectMapper.writeValue(writer, new ApiResponse<Void>("3008", "Password incorrect", null));
        } else {
            response.setStatus(403);
            objectMapper.writeValue(writer, new ApiResponse<String>("3000", "Invalid authentication", exception.getMessage()));
        }

    }
}
