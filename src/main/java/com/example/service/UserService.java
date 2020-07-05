package com.example.service;

import com.example.entities.User;
import com.example.exception.ResourceNotFoundException;
import com.example.model.UserModel;
import com.example.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;

@org.springframework.stereotype.Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public int addUser(UserModel user) {
        long id = user.getId();
        String userName = user.getUserName();
        String email = user.getEmail();
        long passwordHash = user.getPasswordHash();

        User newUser = new User(id, userName, email, passwordHash);
        return this.userRepository.save(newUser).getUserId();
    }
}
