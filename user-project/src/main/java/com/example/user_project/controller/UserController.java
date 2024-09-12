package com.example.user_project.controller;


import com.example.user_project.model.PatchUserRequest;
import com.example.user_project.model.User;
import com.example.user_project.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/user")
@AllArgsConstructor
public class UserController {

    @Autowired
    private UserService service;

    @GetMapping("/users")
    public List<User> getUsers(){
        return service.getUsers();
    }

    @GetMapping("/users/{id}")
    public User getUser(@PathVariable(value="id") Long userId){

        return  Optional.ofNullable(userId)
                        .map(u->Long.valueOf(userId))
                                .map(service::getUser)
                                        .orElseThrow();
    }

    @PostMapping("/post")
    @ResponseStatus(HttpStatus.OK)
    public void createUser(@RequestBody User user){
        service.create(user);
    }

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable("id") String id){

       var user= Optional.ofNullable(id)//returns an empty Optional if the id is null
                .map(Long::valueOf)
                .map(service::getUser)//checks if the user is present in the db or not
                .orElseThrow();

       service.delete(user.getUserId());
    }

    @PatchMapping("/id")
    @ResponseStatus(HttpStatus.OK)
    public void update(@RequestBody User user,@RequestBody PatchUserRequest patchUserRequest){

        service.update(user,patchUserRequest);
    }

    @PutMapping("/{id}")
    public void updateUser(@PathVariable(value="id") String userId,@RequestBody PatchUserRequest patchUserRequest){

        var user= Optional.ofNullable(userId)//returns an empty Optional if the id is null
                .map(u->Long.valueOf(u))
                .map(service::getUser)//checks if the user is present in the db or not
                .orElseThrow();

        service.update(user,patchUserRequest);
    }

    //ResponseBody customization
    @GetMapping("/test")
    @ResponseStatus(HttpStatus.ACCEPTED)
    @ResponseBody//if we use @RestController we don't need to use @ResponseBody annotation, because @RestController has this annotation inside it
    public int test(){
        return 100;
    }


    //ResponseBody customization
    @GetMapping("/testString")
    @ResponseStatus(HttpStatus.ACCEPTED)
    @ResponseBody
    public ResponseEntity<Integer> test1(@RequestHeader(value="number1",defaultValue = "100") int num ){

        HttpHeaders headers=new HttpHeaders();
        headers.add("own","value");
        return new ResponseEntity<>(num,headers,HttpStatus.OK);
    }
}
