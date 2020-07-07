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

    @NonNull
    private String firstName;

    @NonNull
    private String lastName;
    private GenderEnum genderEnum;

    @NonNull
    private long birthDate;

    private void initGenderEnum(String genderString) {
        this.genderEnum = GenderEnum.findEnum(genderString);
        if (this.genderEnum == null) {
            throw new GenderEnumDoesntExistException("Enum for " + genderString + " doesn't eixst");
        }
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
