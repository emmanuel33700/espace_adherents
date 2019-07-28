package fr.espaceadh.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.PropertySource;


@SpringBootApplication(scanBasePackages = "fr.espaceadh")
public class AuthorizationServerApplication extends SpringBootServletInitializer {

    private static final Logger LOGGER = LoggerFactory.getLogger(AuthorizationServerApplication.class);  
    
    
    
    public static void main(String[] args) {

        
        SpringApplication.run(AuthorizationServerApplication.class, args);
    }

}