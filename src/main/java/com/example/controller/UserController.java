package com.example.controller;

import com.example.dto.UserDTO;
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
    public int addUser(@RequestBody UserDTO user) {
        UserModel newUser = new UserModel(user);
        return this.userService.addUser(newUser);
    }

    @GetMapping("/users/{userId}")
    public UserDTO getUserById(@PathVariable(value = "userId") Integer userId)
        throws ResourceNotFoundException {
        UserModel user = this.userService.getUserById(userId);
        return new UserDTO(user);
    }
}
