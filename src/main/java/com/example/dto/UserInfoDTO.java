package com.example.dto;

import com.example.model.UserInfoModel;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserInfoDTO {

    private int userId;

    private String firstName;

    private String lastName;

    private String gender;

    private long birthDate;

    public UserInfoDTO(String firstName, String lastName, String gender, long birthDate) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.gender = gender;
        this.birthDate = birthDate;
    }

    public UserInfoDTO(UserInfoModel userInfoModel) {
        this(userInfoModel.getUserId(), userInfoModel.getFirstName(), userInfoModel.getLastName(),
                userInfoModel.getGenderEnum().toString().toLowerCase(), userInfoModel.getBirthDate());
    }

}
