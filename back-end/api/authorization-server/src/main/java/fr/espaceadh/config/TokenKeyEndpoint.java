package fr.espaceadh.config;

import java.util.Base64;
import java.util.Map;
import com.nimbusds.jose.jwk.JWKMatcher;
import com.nimbusds.jose.jwk.JWKSelector;
import com.nimbusds.jose.jwk.KeyType;
import com.nimbusds.jose.jwk.RSAKey;
import com.nimbusds.jose.jwk.source.JWKSource;
import com.nimbusds.jose.proc.SecurityContext;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/** Historical public key endpoint; the standard JWK endpoint is /oauth/jwks. */
@RestController
public class TokenKeyEndpoint {
    private final JWKSource<SecurityContext> keys;

    public TokenKeyEndpoint(JWKSource<SecurityContext> keys) {
        this.keys = keys;
    }

    @GetMapping("/oauth/token_key")
    public Map<String, String> tokenKey() throws Exception {
        RSAKey key = (RSAKey) keys.get(new JWKSelector(new JWKMatcher.Builder().keyType(KeyType.RSA).build()), null).getFirst();
        String encoded = Base64.getMimeEncoder(64, new byte[]{'\n'}).encodeToString(key.toRSAPublicKey().getEncoded());
        return Map.of("alg", "SHA256withRSA", "value", "-----BEGIN PUBLIC KEY-----\n" + encoded + "\n-----END PUBLIC KEY-----");
    }
}
