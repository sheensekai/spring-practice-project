package com.example.service.impl;

import com.example.entities.User;
import com.example.exception.exists.UserAlreadyExistsException;
import com.example.exception.notfound.UserNotFoundException;
import com.example.model.UserModel;
import com.example.repository.UserRepository;
import com.example.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    public UserServiceImpl(@Autowired UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UserModel addUser(UserModel userModel)
        throws UserAlreadyExistsException {
        if (this.containsByUserName(userModel.getUserName())) {
            throw new UserAlreadyExistsException("User with username " + userModel.getUserName() + " already exists");
        }
        if (this.containsByEmail(userModel.getEmail())) {
            throw new UserAlreadyExistsException("User with email " + userModel.getEmail() + " already exists");
        }

        User newUser = new User(userModel);
        newUser = this.userRepository.save(newUser);
        return new UserModel(newUser);
    }

    public UserModel updateUser(UserModel userModel)
        throws UserNotFoundException {
        if (!this.userRepository.existsById(userModel.getUserId())) {
            throw new UserNotFoundException("User with id " + userModel.getUserId() + " doesn't exist");
        }

        User newUser = new User(userModel);
        newUser = this.userRepository.save(newUser);
        return new UserModel(newUser);
    }

    public UserModel getUserByUserId(int userId)
        throws UserNotFoundException {
        User user = this.userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException("Couldn't find user with id" + userId));
        return new UserModel(user);
    }

    public boolean existsByUserId(int userId) {
        return this.userRepository.existsById(userId);
    }

    public boolean containsByUserName(String userName) {
        return this.userRepository.existsByUserName(userName);
    }

    public boolean containsByEmail(String email) {
        return this.userRepository.existsByEmail(email);
    }
}
