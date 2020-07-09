package com.example.service.impl;

import com.example.entities.UserInfo;
import com.example.exception.exists.ResourceAlreadyExistsException;
import com.example.exception.notfound.ResourceNotFoundException;
import com.example.model.GenderModel;
import com.example.model.UserInfoModel;
import com.example.repository.UserInfoRepository;
import com.example.service.GenderService;
import com.example.service.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserInfoServiceImpl implements UserInfoService {
    private final UserInfoRepository userInfoRepository;
    private final GenderService genderService;

    public UserInfoServiceImpl(
            @Autowired UserInfoRepository userInfoRepository,
            @Autowired GenderService genderService) {
        this.userInfoRepository = userInfoRepository;
        this.genderService = genderService;
    }

    public UserInfoModel addUserInfo(UserInfoModel userInfoModel) {
        if (this.userInfoRepository.existsById(userInfoModel.getUserId())) {
            throw new ResourceAlreadyExistsException("UserInfo with userId " + userInfoModel.getUserId() + " already exists");
        }

        String genderString = userInfoModel.getGenderEnum().toString().toLowerCase();
        GenderModel newUserGenderModel = this.genderService.findGenderByGender(genderString);
        UserInfo newUserInfo = new UserInfo(userInfoModel, newUserGenderModel.getGenderId());

        newUserInfo = this.userInfoRepository.save(newUserInfo);
        return new UserInfoModel(newUserInfo, genderString);
    }

    public UserInfoModel getUserInfoByUserId(int userId) {
        UserInfo userInfo = this.userInfoRepository.findById(userId)
                .orElseThrow( () -> new ResourceNotFoundException("UserInfo with userId " + userId + " doesn't exist"));

        GenderModel foundGenderModel = this.genderService.findGenderByGenderId(userId);
        String genderString = foundGenderModel.getGenderEnum().toString().toLowerCase();
        return new UserInfoModel(userInfo, genderString);
    }

    public UserInfoModel updateUserInfo(UserInfoModel userInfoModel) {
        if (!this.userInfoRepository.existsById(userInfoModel.getUserId())) {
            throw new ResourceNotFoundException("UserInfo with userId " + userInfoModel.getUserId() + " doesn't exist");
        }

        String genderString = userInfoModel.getGenderEnum().toString().toLowerCase();
        GenderModel genderModel = this.genderService.findGenderByGender(genderString);

        UserInfo newUserInfo = new UserInfo(userInfoModel, genderModel.getGenderId());
        newUserInfo = this.userInfoRepository.save(newUserInfo);
        return new UserInfoModel(newUserInfo, genderString);
    }
}
