package com.example.gateway.controller;

import com.example.gateway.model.User;
import com.example.gateway.proxy.UserProxy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;


@RestController
@RequestMapping("v1/users")
public class GatewayUserController {

        @Autowired
        RestTemplate restTemplate;
        @Autowired
        private UserProxy userProxy;

        //under the hood Spring uses @ResponseBody automatically, because we include the @RestController annotation
        //the return type of the method will be in JSON representation
        @GetMapping
        public User[] getUsers(){
                return userProxy.getUsers();

        }

}
