package com.example.controller;

import com.example.dto.UserInfoDTO;
import com.example.exception.ResourceAlreadyExistsException;
import com.example.exception.ResourceNotFoundException;
import com.example.model.UserInfoModel;
import com.example.service.impl.UserInfoServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/userInfo")
public class UserInfoController {
    @Autowired
    private UserInfoServiceImpl userInfoService;

    @PostMapping("/{userId}")
    public UserInfoDTO addUserInfo(@PathVariable(name = "userId") Integer userId, UserInfoDTO userInfoDTO)
        throws ResourceAlreadyExistsException {
        UserInfoModel newUserInfoModel = new UserInfoModel(userInfoDTO);
        newUserInfoModel.setUserId(userId);

        newUserInfoModel = this.userInfoService.addUserInfo(newUserInfoModel);
        return new UserInfoDTO(newUserInfoModel);
    }

    @PutMapping("/{userId}")
    public UserInfoDTO updateUserInfo(@PathVariable(name = "userId") Integer userId, UserInfoDTO userInfoDTO)
        throws ResourceNotFoundException {
        UserInfoModel userInfoModel = new UserInfoModel(userInfoDTO);
        userInfoModel.setUserId(userId);

        userInfoModel = this.userInfoService.updateUserInfo(userInfoModel);
        return new UserInfoDTO(userInfoModel);
    }

    @GetMapping("/{userId}")
    public UserInfoDTO getUserInfo(@PathVariable(name = "userId") Integer userId)
        throws ResourceNotFoundException {
        UserInfoModel foundUserInfoModel =  this.userInfoService.getUserInfoByUserId(userId);
        return new UserInfoDTO(foundUserInfoModel);
    }
}
