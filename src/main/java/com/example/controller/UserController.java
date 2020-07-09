package com.example.controller;

import com.example.UserStatusEnum;
import com.example.dto.UserDTO;
import com.example.dto.UserStatusDTO;
import com.example.exception.exists.ResourceAlreadyExistsException;
import com.example.exception.notfound.ResourceNotFoundException;
import com.example.model.UserModel;
import com.example.model.UserStatusModel;
import com.example.service.UserService;
import com.example.service.UserStatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
public class UserController {
    private final UserService userService;
    private final UserStatusService userStatusService;

    public UserController
            (@Autowired UserService userService,
             @Autowired UserStatusService userStatusService) {
        this.userService = userService;
        this.userStatusService = userStatusService;
    }

    @PostMapping("/")
    public UserDTO addUser(@RequestBody UserDTO userDTO)
        throws ResourceAlreadyExistsException {
        UserModel newUserModel = new UserModel(userDTO);
        newUserModel = this.userService.addUser(newUserModel);

        this.userStatusService.updateUserStatus(userDTO.getUserId(), UserStatusEnum.ONLINE);
        return new UserDTO(newUserModel);
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

        UserStatusModel updatedUserStatusModel = this.userStatusService.updateUserStatus(userId, userStatusEnum);
        return new UserStatusDTO(updatedUserStatusModel);
    }

    @GetMapping("/addUser")
    public UserDTO getUserById(@RequestParam(value = "userId") Integer userId)
        throws ResourceNotFoundException {
        UserModel foundUserModel = this.userService.getUserByUserId(userId);
        return new UserDTO(foundUserModel);
    }
}
