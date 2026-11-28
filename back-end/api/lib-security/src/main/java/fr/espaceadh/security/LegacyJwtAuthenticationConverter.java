package fr.espaceadh.security;

import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.Set;
import org.springframework.core.convert.converter.Converter;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.security.oauth2.server.resource.authentication.JwtGrantedAuthoritiesConverter;

/** Reads both the historical JWT claims and tokens issued by Authorization Server. */
public class LegacyJwtAuthenticationConverter implements Converter<Jwt, AbstractAuthenticationToken> {
    @Override
    public AbstractAuthenticationToken convert(Jwt jwt) {
        Set<GrantedAuthority> authorities = new LinkedHashSet<>(new JwtGrantedAuthoritiesConverter().convert(jwt));
        Object claim = jwt.getClaims().get("authorities");
        if (claim instanceof String roles) {
            for (String role : roles.trim().split("\\s+")) {
                if (!role.isBlank()) authorities.add(new SimpleGrantedAuthority(role));
            }
        } else if (claim instanceof Collection<?> roles) {
            roles.stream().map(Object::toString).map(SimpleGrantedAuthority::new).forEach(authorities::add);
        }
        String username = jwt.getClaimAsString("user_name");
        JwtAuthenticationToken token = new JwtAuthenticationToken(jwt, authorities,
                username != null ? username : jwt.getSubject());
        token.setDetails(jwt.getClaims());
        return token;
    }
}
