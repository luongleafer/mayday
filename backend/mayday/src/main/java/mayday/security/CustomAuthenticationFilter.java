package mayday.security;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import mayday.dto.requests.LoginRequestBody;
import org.jspecify.annotations.Nullable;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.servlet.util.matcher.PathPatternRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;
import tools.jackson.core.JacksonException;
import tools.jackson.databind.ObjectMapper;

import java.io.IOException;

public class CustomAuthenticationFilter extends AbstractAuthenticationProcessingFilter {
    private static final RequestMatcher DEFAULT_PATH_REQUEST_MATCHER = PathPatternRequestMatcher.withDefaults()
            .matcher(HttpMethod.POST, "/api/v1/auth/login");


    public CustomAuthenticationFilter() {
        super(DEFAULT_PATH_REQUEST_MATCHER);
    }

    public CustomAuthenticationFilter(AuthenticationManager authenticationManager, AuthenticationSuccessHandler successHandler) {
        super(DEFAULT_PATH_REQUEST_MATCHER, authenticationManager);
        this.setAuthenticationSuccessHandler(successHandler);
        this.setAuthenticationFailureHandler(new LoginFailureHandler());

    }

    @Override
    public @Nullable Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException, IOException, ServletException {
        if (!request.getMethod().equals("POST")) throw new AuthenticationServiceException("Method unsupported");
        try {
            var reader = request.getReader();
            ObjectMapper objectMapper = new ObjectMapper();
            LoginRequestBody loginRequest = objectMapper.readValue(reader, LoginRequestBody.class);
            UsernamePasswordAuthenticationToken token = UsernamePasswordAuthenticationToken.unauthenticated(
                    loginRequest.getPhone(),
                    loginRequest.getPassword()
            );
            return this.getAuthenticationManager().authenticate(token);
        } catch (JacksonException e) {
            throw new AuthenticationServiceException("Error parsing login request: " + e.getMessage());
        }
//        return super.attemptAuthentication(request, response);
    }
}
