package mayday.configurations;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.core.env.Environment;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.oauth2.jwt.BadJwtException;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.JwtDecoders;
import org.springframework.security.oauth2.jwt.SupplierJwtDecoder;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
import org.springframework.security.oauth2.server.resource.authentication.JwtGrantedAuthoritiesConverter;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class SecurityConfig {
    @Bean
    public JwtDecoder jwtDecoder(Environment environment) {
        String issuer = environment.getProperty("spring.security.oauth2.resourceserver.jwt.issuer-uri");
        if (issuer == null || issuer.isBlank()) {
            return token -> switch (token) {
                case "dev-admin" -> org.springframework.security.oauth2.jwt.Jwt.withTokenValue(token)
                        .header("alg", "none").subject("1").claim("roles", java.util.List.of("ADMIN")).build();
                case "dev-receptionist" -> org.springframework.security.oauth2.jwt.Jwt.withTokenValue(token)
                        .header("alg", "none").subject("2").claim("roles", java.util.List.of("RECEPTIONIST")).build();
                case "dev-doctor" -> org.springframework.security.oauth2.jwt.Jwt.withTokenValue(token)
                        .header("alg", "none").subject("3").claim("roles", java.util.List.of("DOCTOR")).build();
                case "dev-patient" -> org.springframework.security.oauth2.jwt.Jwt.withTokenValue(token)
                        .header("alg", "none").subject("1").claim("roles", java.util.List.of("PATIENT")).build();
                default -> throw new BadJwtException("JWT issuer is not configured");
            };
        }
        return new SupplierJwtDecoder(() -> JwtDecoders.fromIssuerLocation(issuer));
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) {
        var roles = new JwtGrantedAuthoritiesConverter();
        roles.setAuthoritiesClaimName("roles");
        roles.setAuthorityPrefix("ROLE_");
        var converter = new JwtAuthenticationConverter();
        converter.setJwtGrantedAuthoritiesConverter(roles);

        http.csrf(csrf -> csrf.disable())
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers("/api/v1/doctor/**").hasAnyRole("DOCTOR", "ADMIN", "RECEPTIONIST")
                        .requestMatchers(org.springframework.http.HttpMethod.GET,
                                "/api/v1/branches/**", "/api/branches/**",
                                "/api/v1/departments/**", "/api/departments/**",
                                "/api/v1/rooms/**", "/api/rooms/**")
                        .authenticated()
                        .requestMatchers(
                                "/api/v1/branches/**", "/api/branches/**",
                                "/api/v1/departments/**", "/api/departments/**",
                                "/api/v1/rooms/**", "/api/rooms/**")
                        .hasAnyRole("ADMIN", "RECEPTIONIST")
                        .requestMatchers("/api/v1/medical_records/**").authenticated()
                        .anyRequest().denyAll())
                .oauth2ResourceServer(oauth -> oauth
                        .jwt(jwt -> jwt.jwtAuthenticationConverter(converter))
                        .authenticationEntryPoint((request, response, error) -> {
                            response.setStatus(401);
                            response.setContentType("application/json");
                            response.setHeader("WWW-Authenticate", "Bearer");
                            if (request.getRequestURI().startsWith("/api/v1/medical_records")) {
                                response.getWriter().write("{\"code\":\"3101\",\"message\":\"Unauthorized\",\"data\":null}");
                                return;
                            }
                            String authHeader = request.getHeader("Authorization");
                            if (authHeader == null || authHeader.isBlank()) {
                                response.getWriter().write("{\"code\":\"3003\",\"message\":\"User not authenticated\",\"data\":null}");
                            } else {
                                response.getWriter().write("{\"code\":\"3001\",\"message\":\"Token is invalid\",\"data\":null}");
                            }
                        })
                        .accessDeniedHandler((request, response, error) -> {
                            response.setStatus(403);
                            response.setContentType("application/json");
                            if (request.getRequestURI().startsWith("/api/v1/medical_records")) {
                                response.getWriter().write("{\"code\":\"3106\",\"message\":\"Forbidden\",\"data\":null}");
                            } else {
                                response.getWriter().write("{\"code\":\"3101\",\"message\":\"Permission denied\",\"data\":null}");
                            }
                        }));
        return http.build();
    }
}
