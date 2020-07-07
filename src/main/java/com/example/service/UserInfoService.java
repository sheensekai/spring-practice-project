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

    public void addUserInfo(UserInfoModel userInfo) {
        if (this.userInfoRepository.existsById(userInfo.getUserId())) {
            throw new ResourceAlreadyExistsException("UserInfo with userId " + userInfo.getUserId() + " already exists");
        }

        GenderEnum newUserGenderEnum = userInfo.getGenderEnum();
        String genderString = newUserGenderEnum.toString().toLowerCase();
        Gender newUserGender = this.genderRepository.findByGender(genderString)
                .orElseThrow( () -> new ResourceNotFoundException("Gender " + newUserGenderEnum.toString().toLowerCase() + " doesn't exist"));

        int genderId = newUserGender.getId();
        UserInfo newUserInfo = new UserInfo(userInfo, genderId);

        this.userInfoRepository.save(newUserInfo);
    }

    public UserInfoModel getUserInfoByUserId(int userId) {
        UserInfo userInfo = this.userInfoRepository.findById(userId)
                .orElseThrow( () -> new ResourceNotFoundException("UserInfo with userId " + userId + " doesn't exist"));

        String gender = this.genderRepository.findById(userInfo.getGenderId()).get().getGender();
        return new UserInfoModel(userInfo, gender);
    }
}
