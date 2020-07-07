package com.example.controller;

import com.example.dto.UserInfoDTO;
import com.example.entities.UserInfo;
import com.example.exception.ResourceAlreadyExistsException;
import com.example.exception.ResourceNotFoundException;
import com.example.model.UserInfoModel;
import com.example.service.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/userinfo")
public class UserInfoController {
    @Autowired
    private UserInfoService userInfoService;

    @PostMapping("/{userId}")
    public UserInfoDTO addUserInfo(@PathVariable(name = "userId") Integer userId, UserInfoDTO userInfoDTO)
        throws ResourceAlreadyExistsException {
        UserInfoModel newUserInfoModel = new UserInfoModel(userInfoDTO);
        newUserInfoModel = this.userInfoService.addUserInfo(newUserInfoModel);
        return new UserInfoDTO(newUserInfoModel);
    }

    @PutMapping("/{userId}")
    public void updateUserInfo(@PathVariable(name = "userId") Integer userId, UserInfoDTO userInfoDTO)
        throws ResourceNotFoundException {
        UserInfoModel userInfoModel = new UserInfoModel(userInfoDTO);
        this.userInfoService.updateUserInfo(userInfoModel);
    }

    @GetMapping("/{userId}")
    public UserInfoDTO getUserInfo(@PathVariable(name = "userId") Integer userId)
        throws ResourceNotFoundException {
        UserInfoModel foundUserInfoModel =  this.userInfoService.getUserInfoByUserId(userId);
        return new UserInfoDTO(foundUserInfoModel);
    }
}
