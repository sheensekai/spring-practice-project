package com.example.service;

import com.example.model.UserInfoModel;
import org.springframework.stereotype.Service;

@Service
public interface UserInfoService {
    UserInfoModel addUserInfo(UserInfoModel userInfoModel);
    UserInfoModel getUserInfoByUserId(int userId);
    UserInfoModel updateUserInfo(UserInfoModel userInfoModel);
}
