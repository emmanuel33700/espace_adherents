package fr.espaceadh.adherents.config;

import fr.espaceadh.security.LegacyJwtAuthenticationConverter;
import org.springframework.stereotype.Component;

@Component
public class CustomAccessTokenConverter extends LegacyJwtAuthenticationConverter { }
