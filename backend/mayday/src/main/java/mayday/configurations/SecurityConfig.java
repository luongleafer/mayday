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

@Configuration
public class SecurityConfig {
    @Bean
    public JwtDecoder jwtDecoder(Environment environment) {
        String issuer = environment.getProperty("spring.security.oauth2.resourceserver.jwt.issuer-uri");
        if (issuer == null || issuer.isBlank()) {
            return token -> {
                throw new BadJwtException("JWT issuer is not configured");
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
                        .requestMatchers("/api/v1/medical_records", "/api/v1/medical_records/**").permitAll()
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
