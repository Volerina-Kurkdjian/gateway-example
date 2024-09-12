package com.example.gateway.controller;

import com.example.gateway.model.PatchUserRequest;
import com.example.gateway.model.User;
import com.example.gateway.proxy.UserProxy;
import org.apache.hc.core5.http.ContentType;
import org.apache.hc.core5.http.HttpEntity;
import org.apache.hc.core5.http.io.entity.StringEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.net.URI;

@RestController
@RequestMapping("/gateway/users")
public class GatewayUserController {

        @Autowired
        RestTemplate restTemplate;
        @Autowired
        private UserProxy userProxy;

        @PatchMapping("/{id}")
        public ResponseEntity<Void> update(@PathVariable(name="id") String id, @RequestBody PatchUserRequest request){
                RequestEntity<PatchUserRequest> requestEntity = RequestEntity.put(URI.create("/" + id)).body(request);

                try {
                        userProxy.update(id,requestEntity.getBody());
                        return ResponseEntity.noContent().build();
                } catch (Exception e) {
                        // Handle exceptions appropriately
                        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();

                }
        }

        //under the hood Spring uses @ResponseBody automatically, because we include the @RestController annotation
        //the return type of the method will be in JSON representation
        @GetMapping
        public User[] getUsers(){
                return userProxy.getUsers();
        }

        @PostMapping
        public void createUser(@RequestBody User user){
                userProxy.create(user);
        }

        @DeleteMapping("/{id}")
        public void delete(@PathVariable(name="id") String id){
                userProxy.removeUser(id);
        }




}
