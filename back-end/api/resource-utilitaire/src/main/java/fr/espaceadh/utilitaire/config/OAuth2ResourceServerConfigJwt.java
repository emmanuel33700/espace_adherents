package fr.espaceadh.utilitaire.config;

import java.nio.file.Files;
import java.nio.file.Path;
import java.security.interfaces.RSAPublicKey;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.converter.RsaKeyConverters;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class OAuth2ResourceServerConfigJwt {
    @Bean
    public SecurityFilterChain apiSecurity(HttpSecurity http, CustomAccessTokenConverter converter) throws Exception {
        return http.cors(Customizer.withDefaults())
                .csrf(csrf -> csrf.disable())
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(requests -> requests.anyRequest().permitAll())
                .oauth2ResourceServer(resource -> resource.jwt(jwt -> jwt.jwtAuthenticationConverter(converter)))
                .build();
    }

    @Bean
    public JwtDecoder jwtDecoder(Environment env) throws java.io.IOException {
        Path key = Path.of(env.getRequiredProperty("key.public"));
        if (!key.isAbsolute()) {
            Path envFile = Path.of(env.getProperty("ENV_FILE", ".env")).toAbsolutePath();
            key = envFile.getParent().resolve(key).normalize();
        }
        try (var input = Files.newInputStream(key)) {
            RSAPublicKey publicKey = RsaKeyConverters.x509().convert(input);
            return NimbusJwtDecoder.withPublicKey(publicKey).build();
        }
    }
}
