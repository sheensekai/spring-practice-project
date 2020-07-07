package com.example.dto;

import com.example.model.UserInfoModel;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserInfoDTO {

    private int userId;
    private String firstName;
    private String lastName;
    private String gender;
    private long birthDate;

    public UserInfoDTO(UserInfoModel userInfoModel) {
        this.userId = userInfoModel.getUserId();
        this.firstName = userInfoModel.getFirstName();
        this.lastName = userInfoModel.getLastName();
        this.gender = userInfoModel.getGenderEnum().toString().toLowerCase();
        this.birthDate = userInfoModel.getBirthDate();
    }

}
