package com.example.service;

import com.example.entities.User;
import com.example.exception.ResourceAlreadyExistsException;
import com.example.exception.ResourceNotFoundException;
import com.example.model.UserModel;
import com.example.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public UserModel addUser(UserModel user) {
        if (this.containsUserName(user.getUserName())) {
            throw new ResourceAlreadyExistsException("User with username " + user.getUserName() + " already exists");
        }
        if (this.containsEmail(user.getEmail())) {
            throw new ResourceAlreadyExistsException("User with email " + user.getEmail() + " already exists");
        }

        User newUser = new User(user);
        newUser = this.userRepository.save(newUser);
        return new UserModel(newUser);
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
