package fr.espaceadh.config;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.OAuth2ErrorCodes;
import org.springframework.security.web.authentication.AuthenticationConverter;

public class PasswordGrantAuthenticationConverter implements AuthenticationConverter {
    @Override
    public Authentication convert(HttpServletRequest request) {
        if (!"password".equals(request.getParameter("grant_type"))) return null;
        String username = required(request, "username");
        String password = required(request, "password");
        if (request.getParameterValues("grant_type").length != 1 ||
                (request.getParameterValues("scope") != null && request.getParameterValues("scope").length != 1)) {
            throw new OAuth2AuthenticationException(OAuth2ErrorCodes.INVALID_REQUEST);
        }
        String scope = request.getParameter("scope");
        Set<String> scopes = scope == null || scope.isBlank() ? Set.of()
                : Arrays.stream(scope.trim().split("\\s+")).collect(Collectors.toSet());
        return new PasswordGrantAuthenticationToken(SecurityContextHolder.getContext().getAuthentication(),
                username, password, scopes);
    }

    private String required(HttpServletRequest request, String name) {
        String[] values = request.getParameterValues(name);
        if (values == null || values.length != 1 || values[0].isBlank()) {
            throw new OAuth2AuthenticationException(OAuth2ErrorCodes.INVALID_REQUEST);
        }
        return values[0];
    }
}
