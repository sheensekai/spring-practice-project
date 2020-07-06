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
        String userName = user.getUserName();
        String email = user.getEmail();
        long passwordHash = user.getPasswordHash();

        User newUser = new User(userName, email, passwordHash);
        return this.userRepository.save(newUser).getId();
    }

    public UserModel getUserById(int id) {
        User user = this.userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Couldn't find user with id" + id));

        String userName = user.getUserName();
        String email = user.getEmail();
        long passwordHash = user.getPasswordHash();

        return new UserModel(userName, email, passwordHash);
    }
}
