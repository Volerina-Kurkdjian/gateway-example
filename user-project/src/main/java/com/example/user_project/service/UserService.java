package com.example.user_project.service;


import com.example.user_project.model.PatchUserRequest;
import com.example.user_project.model.User;
import org.springframework.stereotype.Service;
import java.util.List;

public interface UserService {

     List<User> getUsers();

     User getUser(Long id);


     void create(User user);

     void delete(Long id);

     void update(User user, PatchUserRequest patchUserRequest);
}
