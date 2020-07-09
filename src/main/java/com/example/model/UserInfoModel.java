package com.example.model;

import com.example.GenderEnum;
import com.example.dto.UserInfoDTO;
import com.example.entities.UserInfo;
import com.example.exception.notfound.GenderEnumDoesntExistException;
import lombok.Data;

@Data
public class UserInfoModel {
    private int userId;
    private String firstName;
    private String lastName;
    private GenderEnum genderEnum;
    private long birthDate;

    private void initGenderEnum(String genderString) {
        this.genderEnum = GenderEnum.findEnum(genderString);
        if (this.genderEnum == null) {
            throw new GenderEnumDoesntExistException("Enum for " + genderString + " doesn't eixst");
        }
    }

    public UserInfoModel(String firstName, String lastName, long birthDate) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthDate = birthDate;
    }

    public UserInfoModel(UserInfoDTO userInfoDTO) {
        this(userInfoDTO.getFirstName(), userInfoDTO.getLastName(), userInfoDTO.getBirthDate());
        this.userId = userInfoDTO.getUserId();
        this.initGenderEnum(userInfoDTO.getGender());
    }

    public UserInfoModel(UserInfo userInfo, String gender) {
        this(userInfo.getFirstName(), userInfo.getLastName(), userInfo.getBirthDate());
        this.userId = userInfo.getUserId();
        this.initGenderEnum(gender);
    }
}
