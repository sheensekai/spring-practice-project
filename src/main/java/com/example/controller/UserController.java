package com.example.controller;

import com.example.dto.UserDTO;
import com.example.exception.ResourceAlreadyExistsException;
import com.example.exception.ResourceNotFoundException;
import com.example.model.UserModel;
import com.example.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping("/users")
    public int addUser(@RequestBody UserDTO user)
        throws ResourceAlreadyExistsException {
        UserModel newUser = new UserModel(user);

        if (this.userService.containsUserName(newUser.getUserName())) {
            throw new ResourceAlreadyExistsException("User with username " + newUser.getUserName() + " already exists");
        }
        if (this.userService.containsEmail(newUser.getEmail())) {
            throw new ResourceAlreadyExistsException("User with email " + newUser.getEmail() + " already exists");
        }

        return this.userService.addUser(newUser);
    }

    @GetMapping("/users/{userId}")
    public UserDTO getUserById(@PathVariable(value = "userId") Integer userId)
        throws ResourceNotFoundException {
        UserModel user = this.userService.getUserById(userId);
        return new UserDTO(user);
    }
}
