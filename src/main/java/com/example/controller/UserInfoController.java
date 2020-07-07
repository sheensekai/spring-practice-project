package com.example.controller;

import com.example.dto.UserInfoDTO;
import com.example.exception.ResourceAlreadyExistsException;
import com.example.exception.ResourceNotFoundException;
import com.example.model.UserInfoModel;
import com.example.service.GenderService;
import com.example.service.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/userinfo")
public class UserInfoController {
    @Autowired
    private UserInfoService userInfoService;

    @PostMapping("/{userId}")
    public void addUserInfo(@PathVariable(name = "userId") Integer userId, UserInfoDTO userInfoDTO)
        throws ResourceAlreadyExistsException {
        UserInfoModel newUserInfoModel = new UserInfoModel(userInfoDTO);
        this.userInfoService.addUserInfo(newUserInfoModel);
    }

    @PutMapping("/{userId}")
    public void updateUserInfo(@PathVariable(name = "userId") Integer userId, UserInfoDTO userInfoDTO)
        throws ResourceNotFoundException {
        UserInfoModel userInfoModel = new UserInfoModel(userInfoDTO);
        this.userInfoService.updateUserInfo(userInfoModel);
    }
}
