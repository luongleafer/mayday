package mayday.configurations;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) {
        // Tạm thời gỡ bỏ cài đặt bảo mật của Spring Security
        httpSecurity.authorizeHttpRequests(
                authorize -> authorize.anyRequest().permitAll()
        ).csrf(
                csrf -> csrf.disable()
        );
        return httpSecurity.build();
    }
}
