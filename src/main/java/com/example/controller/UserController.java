package com.example.controller;

import com.example.UserStatusEnum;
import com.example.dto.UserDTO;
import com.example.dto.UserStatusDTO;
import com.example.exception.ResourceAlreadyExistsException;
import com.example.exception.ResourceNotFoundException;
import com.example.model.UserModel;
import com.example.model.UserStatusModel;
import com.example.service.impl.UserServiceImpl;
import com.example.service.impl.UserStatusServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
public class UserController {
    @Autowired
    private UserServiceImpl userService;

    @Autowired
    private UserStatusServiceImpl userStatusService;

    @PostMapping("/")
    public UserDTO addUser(@RequestBody UserDTO user)
        throws ResourceAlreadyExistsException {
        UserModel newUser = new UserModel(user);
        newUser = this.userService.addUser(newUser);

        this.userStatusService.updateUserStatus(user.getUserId(), UserStatusEnum.ONLINE);
        return new UserDTO(newUser);
    }

    @PutMapping("/updateStatus")
    public UserStatusDTO updateUserOnlineStatus(
            @RequestParam(value = "userId") Integer userId,
            @RequestParam(value = "newStatus") String newStatus)
        throws ResourceNotFoundException {
        UserStatusEnum userStatusEnum = UserStatusEnum.findEnum(newStatus);
        if (userStatusEnum == null) {
            throw new ResourceNotFoundException("Enum for " + newStatus + " doesn't exist");
        }

        UserStatusModel userStatusModel = this.userStatusService.updateUserStatus(userId, userStatusEnum);
        return new UserStatusDTO(userStatusModel);
    }

    @GetMapping("/addUser")
    public UserDTO getUserById(@RequestParam(value = "userId") Integer userId)
        throws ResourceNotFoundException {
        UserModel user = this.userService.getUserByUserId(userId);
        return new UserDTO(user);
    }
}
