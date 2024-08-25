package com.example.gateway.proxy;

import com.example.gateway.model.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component //it's going to be injected inside the IoC Container, we can use @Autowired when using it in other classes
public class UserProxy {

    private final RestTemplate restTemplate;
    private final String url;

    public UserProxy(RestTemplate restTemplate,@Value("${user.url}") String url) {
        this.restTemplate = restTemplate;
        this.url = url;
    }

    public User[] getUsers() {
        //https://localhost:8082/v1 --> will call
        return restTemplate.getForObject(url,User[].class);
    }
}
