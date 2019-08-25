package fr.espaceadh.adherents.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.expression.method.MethodSecurityExpressionHandler;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.method.configuration.GlobalMethodSecurityConfiguration;

@Configuration
//@EnableGlobalMethodSecurity(prePostEnabled = true)
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true, proxyTargetClass = false)
public class MethodSecurityConfig extends GlobalMethodSecurityConfiguration {

    
    @Autowired
    ApplicationContext applicationContext;
    
    
    @Override
    protected MethodSecurityExpressionHandler createExpressionHandler() {
        CustomMethodSecurityExpressionHandler handler = new  CustomMethodSecurityExpressionHandler();
        handler.setApplicationContext(applicationContext);
        handler.setPermissionEvaluator(new CustomPermissionEvaluator());
        
        return handler;
    }
    
    

    
    /**
    @Override
    protected MethodSecurityExpressionHandler createExpressionHandler() {
        CustomMethodSecurityExpressionHandler expressionHandler = 
        new CustomMethodSecurityExpressionHandler();
        expressionHandler.setPermissionEvaluator(new CustomPermissionEvaluator());
        return expressionHandler;

      //  return new OAuth2MethodSecurityExpressionHandler();
    }
    * **/

}