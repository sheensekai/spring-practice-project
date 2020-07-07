package com.example.controller;

import com.example.GenderEnum;
import com.example.dto.UserInfoDTO;
import com.example.entities.Gender;
import com.example.entities.UserInfo;
import com.example.exception.ResourceAlreadyExistsException;
import com.example.exception.ResourceNotFoundException;
import com.example.model.GenderModel;
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
    @Autowired
    private GenderService userGenderService;

    @PostMapping("/{userId}")
    public void addUserInfo(@PathVariable(name = "userId") Integer userId, UserInfoDTO userInfo)
        throws ResourceAlreadyExistsException {
        UserInfoModel newUserInfoModel = new UserInfoModel(userInfo);
        this.userInfoService.addUserInfo(newUserInfoModel);
    }
}
