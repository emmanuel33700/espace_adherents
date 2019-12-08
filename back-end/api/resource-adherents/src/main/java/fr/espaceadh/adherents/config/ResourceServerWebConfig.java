package fr.espaceadh.adherents.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableWebMvc
@ComponentScan({ "fr.espaceadh.adherent.controller" })
public class ResourceServerWebConfig implements WebMvcConfigurer {
    //
}
