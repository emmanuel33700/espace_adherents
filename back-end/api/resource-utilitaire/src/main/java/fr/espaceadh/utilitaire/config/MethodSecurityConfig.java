package fr.espaceadh.utilitaire.config;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.expression.method.MethodSecurityExpressionHandler;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;

@Configuration
@EnableMethodSecurity(securedEnabled = true)
public class MethodSecurityConfig {
    @Bean
    public static MethodSecurityExpressionHandler methodSecurityExpressionHandler(ApplicationContext context) {
        CustomMethodSecurityExpressionHandler handler = new CustomMethodSecurityExpressionHandler();
        handler.setApplicationContext(context);
        handler.setPermissionEvaluator(new CustomPermissionEvaluator());
        return handler;
    }
}
