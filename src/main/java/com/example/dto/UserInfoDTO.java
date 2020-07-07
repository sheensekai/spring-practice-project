package com.example.dto;

import com.example.model.UserInfoModel;
import lombok.Data;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class UserInfoDTO {

    private int userId;

    @NonNull
    private String firstName;

    @NonNull
    private String lastName;

    @NonNull
    private String gender;

    @NonNull
    private long birthDate;

    public UserInfoDTO(UserInfoModel userInfoModel) {
        this(userInfoModel.getFirstName(), userInfoModel.getLastName(),
                userInfoModel.getGenderEnum().toString().toLowerCase(), userInfoModel.getBirthDate());
        this.userId = userInfoModel.getUserId();
    }

}
