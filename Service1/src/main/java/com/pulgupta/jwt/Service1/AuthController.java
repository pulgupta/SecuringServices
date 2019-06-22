package com.pulgupta.jwt.Service1;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class AuthController {

    @RequestMapping(value = "/auth", method = RequestMethod.GET)
    public String authenticateUser(String username, String password) {
        return new RestTemplate().getForEntity("http://localhost:8080/gettoken?username="+username+"&password="+password, String.class).getBody();
    }
}
