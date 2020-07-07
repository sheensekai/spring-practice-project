package com.example.model;

import com.example.GenderEnum;
import com.example.dto.UserInfoDTO;
import com.example.entities.UserInfo;
import com.example.exception.GenderEnumDoesntExistException;
import lombok.Data;

@Data
public class UserInfoModel {
    private int userId;
    private String firstName;
    private String lastName;
    private GenderEnum genderEnum;
    private long birthDate;

    public UserInfoModel(UserInfoDTO user) {
        this.userId = user.getUserId();
        this.firstName = user.getFirstName();
        this.lastName = user.getLastName();

        this.genderEnum = GenderEnum.findEnum(user.getGender());
        if (this.genderEnum == null) {
            throw new GenderEnumDoesntExistException("Enum for " + user.getGender() + " doesn't exist");
        }

        this.birthDate = user.getBirthDate();
    }

    public UserInfoModel(UserInfo userInfo, String gender) {
        this.userId = userInfo.getUserId();
        this.firstName = userInfo.getFirstName();
        this.lastName = userInfo.getLastName();

        this.genderEnum = GenderEnum.findEnum(gender);
        if (this.genderEnum == null) {
            throw new GenderEnumDoesntExistException("Enum for " + gender + " doesn't eixst");
        }

        this.birthDate = userInfo.getBirthDate();
    }
}
