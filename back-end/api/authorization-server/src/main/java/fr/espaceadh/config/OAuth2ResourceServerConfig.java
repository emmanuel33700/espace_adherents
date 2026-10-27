package fr.espaceadh.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@Profile("mvc")
public class OAuth2ResourceServerConfig {
    @Bean
    @Order(0)
    SecurityFilterChain employeeSecurity(HttpSecurity http) throws Exception {
        return http.securityMatcher("/employee")
                .authorizeHttpRequests(requests -> requests.anyRequest().hasRole("ADMIN"))
                .oauth2ResourceServer(resource -> resource.jwt(Customizer.withDefaults())).build();
    }
}
