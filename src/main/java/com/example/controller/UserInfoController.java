package com.example.controller;

import com.example.dto.UserInfoDTO;
import com.example.exception.exists.UserInfoAlreadyExistsException;
import com.example.exception.notfound.UserInfoNotFoundException;
import com.example.exception.notfound.UserNotFoundException;
import com.example.model.UserInfoModel;
import com.example.service.UserInfoService;
import com.example.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/userInfo")
public class UserInfoController {
    private final UserInfoService userInfoService;
    private final UserService userService;

    public UserInfoController(
            @Autowired UserInfoService userInfoService,
            @Autowired UserService userService) {
        this.userInfoService = userInfoService;
        this.userService = userService;
    }

    @PostMapping("/addUserInfo/{userId}")
    public UserInfoDTO addUserInfo(@PathVariable(name = "userId") Integer userId, @RequestBody UserInfoDTO userInfoDTO)
        throws UserNotFoundException, UserInfoAlreadyExistsException {
        UserInfoModel newUserInfoModel = new UserInfoModel(userInfoDTO);
        newUserInfoModel.setUserId(userId);

        if (!userService.existsByUserId(userId)) {
            throw new UserNotFoundException("User with userId " + userId + " doesn't exist");
        }

        newUserInfoModel = this.userInfoService.addUserInfo(newUserInfoModel);
        return new UserInfoDTO(newUserInfoModel);
    }

    @PutMapping("/updateUserInfo/{userId}")
    public UserInfoDTO updateUserInfo(@PathVariable(name = "userId") Integer userId, @RequestBody UserInfoDTO userInfoDTO)
        throws UserNotFoundException, UserInfoNotFoundException {
        UserInfoModel updatedUserInfoModel = new UserInfoModel(userInfoDTO);
        updatedUserInfoModel.setUserId(userId);

        if (!userService.existsByUserId(userId)) {
            throw new UserNotFoundException("User with userId " + userId + " doesn't exist");
        }

        updatedUserInfoModel = this.userInfoService.updateUserInfo(updatedUserInfoModel);
        return new UserInfoDTO(updatedUserInfoModel);
    }

    @GetMapping("/getUserInfo/{userId}")
    public UserInfoDTO getUserInfo(@PathVariable(name = "userId") Integer userId)
        throws UserNotFoundException, UserInfoNotFoundException {

        if (!userService.existsByUserId(userId)) {
            throw new UserNotFoundException("User with userId " + userId + " doesn't exist");
        }

        UserInfoModel foundUserInfoModel =  this.userInfoService.getUserInfoByUserId(userId);
        return new UserInfoDTO(foundUserInfoModel);
    }
}
