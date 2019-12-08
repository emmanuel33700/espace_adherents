package fr.espaceadh.authorization.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableWebMvc
@ComponentScan({ "fr.espaceadh.authorization.controller", "fr.espaceadh.authorization.service" })
public class ResourceServerWebConfig implements WebMvcConfigurer {
    //
}
