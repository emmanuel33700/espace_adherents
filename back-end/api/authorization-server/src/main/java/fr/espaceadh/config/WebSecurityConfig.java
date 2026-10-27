package fr.espaceadh.config;

import javax.sql.DataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import fr.espaceadh.security.LegacyJwtAuthenticationConverter;

@Configuration
public class WebSecurityConfig {
    @Bean
    @Order(2)
    SecurityFilterChain applicationSecurity(HttpSecurity http) throws Exception {
        return http.cors(Customizer.withDefaults())
                .csrf(csrf -> csrf.ignoringRequestMatchers("/oauth/token"))
                .authorizeHttpRequests(requests -> requests
                        .requestMatchers("/oauth/token_key", "/error").permitAll()
                        .anyRequest().authenticated())
                .formLogin(Customizer.withDefaults())
                .oauth2ResourceServer(resource -> resource.jwt(jwt ->
                        jwt.jwtAuthenticationConverter(new LegacyJwtAuthenticationConverter())))
                .build();
    }

    @Bean
    DataSource dataSource(Environment env) {
        DriverManagerDataSource source = new DriverManagerDataSource();
        source.setDriverClassName(env.getRequiredProperty("jdbc.driverClassName"));
        source.setUrl(env.getRequiredProperty("jdbc.url"));
        source.setUsername(env.getRequiredProperty("jdbc.user"));
        source.setPassword(env.getRequiredProperty("jdbc.pass"));
        return source;
    }

    @Bean
    UserDetailsService userDetailsService(DataSource dataSource) {
        JdbcUserDetailsManager users = new JdbcUserDetailsManager(dataSource);
        users.setUsersByUsernameQuery("SELECT username, password, enabled FROM users WHERE username = ?");
        users.setAuthoritiesByUsernameQuery("SELECT username, authority FROM authorities WHERE username = ?");
        return users;
    }

    @Bean
    BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    AuthenticationManager authenticationManager(UserDetailsService users, BCryptPasswordEncoder encoder) {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider(users);
        provider.setPasswordEncoder(encoder);
        return new ProviderManager(provider);
    }
}
