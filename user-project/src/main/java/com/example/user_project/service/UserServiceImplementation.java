package com.example.user_project.service;

import com.example.user_project.model.PatchUserRequest;
import com.example.user_project.model.User;
import com.example.user_project.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@AllArgsConstructor
@Transactional
public class UserServiceImplementation implements UserService{

    @Autowired
    private UserRepository userRepository;

    @Override
    public List<User> getUsers() {
        return userRepository.findAll();
    }

    @Override
    public User getUser(Long id) {
        return userRepository.findByUserId(id);
    }

    @Override
    public void create(User user) {
        userRepository.save(user);
    }

    @Override
    public void delete(Long id) {
       userRepository.deleteByUserId(id);
    }

    @Override
    public void update(User user, PatchUserRequest patchUserRequest) {
       updateUser(user,patchUserRequest);
       userRepository.save(user);

    }

    public void updateUser(User user,PatchUserRequest patchUserRequest){
        if(patchUserRequest.getEmail()!=null){
            user.setEmail(patchUserRequest.getEmail());
        }
        if(patchUserRequest.getFirstName()!=null){
            user.setFirstName(patchUserRequest.getFirstName());
        }
        if(patchUserRequest.getLastName()!=null){
            user.setLastName(patchUserRequest.getLastName());
        }

    }
}
