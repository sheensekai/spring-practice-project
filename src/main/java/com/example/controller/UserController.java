package com.example.controller;

import com.example.dto.UserDTO;
import com.example.exception.ResourceNotFoundException;
import com.example.model.UserModel;
import com.example.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping("/users")
    public int addUser(@RequestBody UserDTO user) {
        String userName = user.getUserName();
        String email = user.getEmail();
        long passwordHash = user.getPasswordHash();

        UserModel newUser = new UserModel(userName, email, passwordHash);
        return this.userService.addUser(newUser);
    }

    @GetMapping("/users/{userId}")
    public UserDTO getUserById(@PathVariable(value = "userId") Integer userId)
        throws ResourceNotFoundException {
        UserModel user = this.userService.getUserById(userId);

        String userName = user.getUserName();
        String email = user.getEmail();
        long passwordHash = user.getPasswordHash();

        return new UserDTO(userName, email, passwordHash);
    }
}
