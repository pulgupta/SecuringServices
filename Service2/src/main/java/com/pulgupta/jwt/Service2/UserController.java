package com.pulgupta.jwt.Service2;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.InvalidClaimException;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.exceptions.SignatureVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.pulgupta.jwt.Service2.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.UnsupportedEncodingException;

@RestController
public class UserController {

    Logger logger = LoggerFactory.getLogger(UserController.class);

    @RequestMapping(value="/user/{username}", method = RequestMethod.GET)
    public ResponseEntity<User> getUser(@PathVariable(value="username", required = true) String username, @RequestHeader(value="Authorization") String jwt) {
        String token = jwt.substring(jwt.lastIndexOf("Bearer ")+7);
        try {
            JWTVerifier verifier = JWT.require(getAlgorithm())
                    .withIssuer("pulgupta")
                    .build();
            verifier.verify(token);
            DecodedJWT decode = JWT.decode(token);
            String username1 = decode.getClaim("preferred_username").asString();
            if(!username.equals(username1)){
                logger.warn("Token owner and request user are different. Not authorised");
                return new ResponseEntity<>(HttpStatus.FORBIDDEN);
            }
        } catch (UnsupportedEncodingException | SignatureVerificationException | JWTDecodeException | InvalidClaimException e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        User supplier = new User(username, "pulgupta", "wxyz", "Delhi");
        return new ResponseEntity<User>(supplier, HttpStatus.OK);
    }

    private Algorithm getAlgorithm() throws UnsupportedEncodingException {
        return Algorithm.HMAC256("secret");
    }
}
