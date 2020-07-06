package com.example.service;

import com.example.entities.User;
import com.example.exception.ResourceNotFoundException;
import com.example.model.UserModel;
import com.example.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public int addUser(UserModel user) {
        User newUser = new User(user);
        return this.userRepository.save(newUser).getId();
    }

    public UserModel getUserById(int id) {
        User user = this.userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Couldn't find user with id" + id));
        return new UserModel(user);
    }

    public boolean containsUserName(String userName) {
        return this.userRepository.existsByUserName(userName);
    }

    public boolean containsEmail(String email) {
        return this.userRepository.existsByEmail(email);
    }
}
