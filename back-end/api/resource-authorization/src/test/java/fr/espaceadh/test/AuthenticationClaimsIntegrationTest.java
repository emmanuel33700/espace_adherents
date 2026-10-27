package fr.espaceadh.test;

import static org.junit.Assert.assertTrue;
import io.restassured.RestAssured;
import io.restassured.response.Response;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.test.context.junit4.SpringRunner;

import fr.espaceadh.authorization.config.ResourceServerApplication;

//Before running this test make sure authorization server is running   

@RunWith(SpringRunner.class)
@SpringBootTest(classes = ResourceServerApplication.class, webEnvironment = WebEnvironment.RANDOM_PORT)
public class AuthenticationClaimsIntegrationTest {

    @Autowired
    private JwtDecoder jwtDecoder;

    @Test
    public void whenTokenDontContainIssuer_thenSuccess() {
        final String tokenValue = obtainAccessToken("fooClientIdPassword", "john", "123");
        final Jwt auth = jwtDecoder.decode(tokenValue);
        System.out.println(tokenValue);
        System.out.println(auth);
        assertTrue(auth.getExpiresAt().isAfter(java.time.Instant.now()));
        System.out.println(auth.getClaims());

        Map<String, Object> details = (Map<String, Object>) auth.getClaims();
        assertTrue(details.containsKey("organization"));
        System.out.println(details.get("organization"));
    }

    private String obtainAccessToken(String clientId, String username, String password) {
        final Map<String, String> params = new HashMap<String, String>();
        params.put("grant_type", "password");
        params.put("client_id", clientId);
        params.put("username", username);
        params.put("password", password);
        final Response response = RestAssured.given().auth().preemptive().basic(clientId, "secret").and().with().params(params).when().post("http://localhost:8081/spring-security-oauth-server/oauth/token");
        return response.jsonPath().getString("access_token");
    }

}
