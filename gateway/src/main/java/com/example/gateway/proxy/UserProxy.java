package com.example.gateway.proxy;

import com.example.gateway.model.PatchUserRequest;
import com.example.gateway.model.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

@Component //it's going to be injected inside the IoC Container, we can use @Autowired when using it in other classes
public class UserProxy {

    private final RestTemplate restTemplate;
    private final String url;
//get the url as an environment variable

    //constructor injector, no need to use @Autowired
    public UserProxy(RestTemplate restTemplate,@Value("${user.url}") String url) {
        this.restTemplate = restTemplate;
        this.url = url;
    }

    public User[] getUsers() {
        //https://localhost:8082 --> will call
        return restTemplate.getForObject(url,User[].class);
    }

    public void update(String id, PatchUserRequest patchUserRequest){
        restTemplate.patchForObject(url+"/{id}",patchUserRequest,Void.class,Map.of("id",id));
    }

    public void create(User user) {
        restTemplate.postForObject(url,user,User.class);
    }

    public void removeUser(String id){
        restTemplate.delete(url+"{id}", Map.of("id",id));
    }


}
