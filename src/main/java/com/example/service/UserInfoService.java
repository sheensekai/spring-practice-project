package com.example.service;

import com.example.GenderEnum;
import com.example.entities.Gender;
import com.example.entities.UserInfo;
import com.example.exception.ResourceAlreadyExistsException;
import com.example.exception.ResourceNotFoundException;
import com.example.model.UserInfoModel;
import com.example.repository.GenderRepository;
import com.example.repository.UserInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserInfoService {
    @Autowired
    private UserInfoRepository userInfoRepository;
    @Autowired
    private GenderRepository genderRepository;

    public void addUserInfo(UserInfoModel userInfoModel) {
        if (this.userInfoRepository.existsById(userInfoModel.getUserId())) {
            throw new ResourceAlreadyExistsException("UserInfo with userId " + userInfoModel.getUserId() + " already exists");
        }

        GenderEnum newUserGenderEnum = userInfoModel.getGenderEnum();
        String genderString = newUserGenderEnum.toString().toLowerCase();
        Gender newUserGender = this.genderRepository.findByGender(genderString)
                .orElseThrow( () -> new ResourceNotFoundException("Gender " + newUserGenderEnum.toString().toLowerCase() + " doesn't exist"));

        int genderId = newUserGender.getId();
        UserInfo newUserInfo = new UserInfo(userInfoModel, genderId);

        this.userInfoRepository.save(newUserInfo);
    }

    public UserInfoModel getUserInfoByUserId(int userId) {
        UserInfo userInfo = this.userInfoRepository.findById(userId)
                .orElseThrow( () -> new ResourceNotFoundException("UserInfo with userId " + userId + " doesn't exist"));

        String genderString = this.genderRepository.findById(userInfo.getGenderId()).get().getGender();
        return new UserInfoModel(userInfo, genderString);
    }

    public void updateUserInfo(UserInfoModel userInfoModel) {
        if (!this.userInfoRepository.existsById(userInfoModel.getUserId())) {
            throw new ResourceNotFoundException("UserInfo with userId " + userInfoModel.getUserId() + " doesn't exist");
        }

        String genderString = userInfoModel.getGenderEnum().toString().toLowerCase();
        Gender gender = this.genderRepository.findByGender(genderString)
                .orElseThrow( () -> new ResourceNotFoundException("Gender " + genderString + " doesn't exist"));

        UserInfo newUserInfo = new UserInfo(userInfoModel, gender.getId());
        this.userInfoRepository.save(newUserInfo);
    }
}
