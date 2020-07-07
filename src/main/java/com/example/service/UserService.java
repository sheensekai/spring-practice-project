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

    public UserModel updateUser(UserModel user) {
        if (!this.userRepository.existsById(user.getUserId())) {
            throw new ResourceNotFoundException("User with id " + user.getUserId() + " doesn't exist");
        }

        User newUser = new User(user);
        newUser = this.userRepository.save(newUser);
        return new UserModel(newUser);
    }

    public UserModel getUserByUserid(int userId) {
        User user = this.userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("Couldn't find user with id" + userId));
        return new UserModel(user);
    }

    public boolean existsByUserId(int userId) {
        return this.userRepository.existsById(userId);
    }

    public boolean containsUserName(String userName) {
        return this.userRepository.existsByUserName(userName);
    }

    public boolean containsEmail(String email) {
        return this.userRepository.existsByEmail(email);
    }
}
