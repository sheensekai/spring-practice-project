package com.example.service;

import com.example.model.UserInfoModel;

public interface UserInfoService {
    UserInfoModel addUserInfo(UserInfoModel userInfoModel);
    UserInfoModel getUserInfoByUserId(int userId);
    UserInfoModel updateUserInfo(UserInfoModel userInfoModel);
}
