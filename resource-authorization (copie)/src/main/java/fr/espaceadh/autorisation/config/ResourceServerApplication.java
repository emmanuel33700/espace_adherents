package fr.espaceadh.autorisation.config;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan ({"fr.espaceadh.autorisation"})
public class ResourceServerApplication extends SpringBootServletInitializer {

    public static void main(String[] args) {
        SpringApplication.run(ResourceServerApplication.class, args);
    }

}