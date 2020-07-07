package com.example.service;

import com.example.GenderEnum;
import com.example.entities.Gender;
import com.example.entities.UserInfo;
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
        GenderEnum newUserGenderEnum = userInfo.getGenderEnum();
        Gender newUserGender = this.genderRepository.findByGender(newUserGenderEnum.toString())
                .orElseThrow( () -> new ResourceNotFoundException("Gender " + newUserGenderEnum.toString().toLowerCase() + " doesn't exist in database"));

        int genderId = newUserGender.getId();
        UserInfo newUserInfo = new UserInfo(userInfo, genderId);
        this.userInfoRepository.save(newUserInfo);
    }
}
