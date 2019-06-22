package com.pulgupta.jwt.authService;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.pulgupta.jwt.authService.model.Credentials;
import org.springframework.web.bind.annotation.*;

import java.io.UnsupportedEncodingException;

@RestController
public class TokenGenerator {
    @RequestMapping(value = "/auth", method = RequestMethod.POST)
    public String getJWTToken(@RequestBody Credentials credentials) throws UnsupportedEncodingException {
        try {
            // Validate username and password
            boolean isValid = validateCredentials(credentials.getUsername(), credentials.getPassword());
            Algorithm algorithm = Algorithm.HMAC256("secret");
            String token = JWT.create()
                    .withIssuer("pulgupta")
                    .withSubject(credentials.getUsername())
                    .withClaim("preferred_username", credentials.getUsername())
                    .sign(algorithm);
            return token;
        } catch (UnsupportedEncodingException exception){
            throw exception;
        } catch (JWTCreationException exception){
            throw exception;
        }
    }

    private boolean validateCredentials(String username, String password) {
        return true;
    }
}
