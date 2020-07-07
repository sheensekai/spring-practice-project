package com.example.model;

import com.example.GenderEnum;
import com.example.dto.UserInfoDTO;
import com.example.entities.UserInfo;
import com.example.exception.GenderEnumDoesntExistException;
import lombok.Data;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class UserInfoModel {
    private int userId;
    @NonNull private String firstName;
    @NonNull private String lastName;
    private GenderEnum genderEnum;
    @NonNull private long birthDate;

    public UserInfoModel(UserInfoDTO userInfoDTO) {
        this(userInfoDTO.getFirstName(), userInfoDTO.getLastName(), userInfoDTO.getBirthDate());

        this.genderEnum = GenderEnum.findEnum(userInfoDTO.getGender());
        if (this.genderEnum == null) {
            throw new GenderEnumDoesntExistException("Enum for " + userInfoDTO.getGender() + " doesn't eixst");
        }
    }

    public UserInfoModel(UserInfo userInfo, String gender) {
        this(userInfo.getFirstName(), userInfo.getLastName(), userInfo.getBirthDate());

        this.genderEnum = GenderEnum.findEnum(gender);
        if (this.genderEnum == null) {
            throw new GenderEnumDoesntExistException("Enum for " + gender + " doesn't eixst");
        }
    }
}
