package mayday.configurations;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.core.env.Environment;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.oauth2.jwt.BadJwtException;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.JwtDecoders;
import org.springframework.security.oauth2.jwt.SupplierJwtDecoder;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
import org.springframework.security.oauth2.server.resource.authentication.JwtGrantedAuthoritiesConverter;
import org.springframework.security.web.SecurityFilterChain;

import java.util.List;

@Configuration
public class SecurityConfig {

    @Bean
    public JwtDecoder jwtDecoder(Environment environment) {
        String issuer = environment.getProperty("spring.security.oauth2.resourceserver.jwt.issuer-uri");
        if (issuer == null || issuer.isBlank()) {
            return token -> switch (token) {
                case "dev-admin" -> Jwt.withTokenValue(token)
                        .header("alg", "none").subject("1").claim("roles", List.of("ADMIN")).build();
                case "dev-receptionist" -> Jwt.withTokenValue(token)
                        .header("alg", "none").subject("2").claim("roles", List.of("RECEPTIONIST")).build();
                case "dev-doctor" -> Jwt.withTokenValue(token)
                        .header("alg", "none").subject("3").claim("roles", List.of("DOCTOR")).build();
                case "dev-patient" -> Jwt.withTokenValue(token)
                        .header("alg", "none").subject("1").claim("roles", List.of("PATIENT")).build();
                default -> {
                    // Hỗ trợ token dạng bearer-doctor-ID hoặc bearer-patient-ID cho kiểm thử linh hoạt
                    if (token.startsWith("doctor-")) {
                        String id = token.substring("doctor-".length());
                        yield Jwt.withTokenValue(token)
                                .header("alg", "none").subject(id).claim("roles", List.of("DOCTOR")).build();
                    }
                    if (token.startsWith("patient-")) {
                        String id = token.substring("patient-".length());
                        yield Jwt.withTokenValue(token)
                                .header("alg", "none").subject(id).claim("roles", List.of("PATIENT")).build();
                    }
                    throw new BadJwtException("JWT issuer is not configured");
                }
            };
        }
        return new SupplierJwtDecoder(() -> JwtDecoders.fromIssuerLocation(issuer));
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
                        .requestMatchers(
                                "/api/v1/prescriptions", "/api/v1/prescriptions/**",
                                "/api/v1/lab-requests", "/api/v1/lab-requests/**",
                                "/api/v1/lab-results", "/api/v1/lab-results/**",
                                "/api/v1/medical_records", "/api/v1/medical_records/**"
                        ).permitAll()
                        .anyRequest().permitAll())
                .oauth2ResourceServer(oauth -> oauth
                        .jwt(jwt -> jwt.jwtAuthenticationConverter(converter))
                        .authenticationEntryPoint((request, response, error) -> {
                            response.setStatus(401);
                            response.setContentType("application/json");
                            response.setHeader("WWW-Authenticate", "Bearer");
                            response.getWriter().write("{\"code\":\"3101\",\"message\":\"Unauthorized\",\"data\":null}");
                        }));
        return http.build();
    }
}
