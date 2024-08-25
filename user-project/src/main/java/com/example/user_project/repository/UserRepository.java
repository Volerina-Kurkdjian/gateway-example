package com.example.user_project.repository;

import com.example.user_project.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {

    User findByUserId(Long id);
    void deleteByUserId(Long id);

}
