package com.pulgupta.jwt.Service1;

import com.pulgupta.jwt.Service1.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

@RestController
public class UserController {

    Logger logger = LoggerFactory.getLogger(UserController.class);

    @RequestMapping(value = "/user", method = RequestMethod.GET)
    public User getUserDetails(@RequestParam String username, @RequestHeader(value="Authorization") String jwt) {
        logger.info("Get User Called");
        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(jwt);
        HttpEntity<String> entity = new HttpEntity<>("parameters", headers);
        return new RestTemplate().exchange("http://localhost:8082/user/"+username, HttpMethod.GET, entity, User.class).getBody();
    }
}
