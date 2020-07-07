package com.example.model;

import com.example.GenderEnum;
import com.example.dto.UserInfoDTO;
import com.example.exception.GenderEnumDoesntExistException;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
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
            throw new GenderEnumDoesntExistException("Enum " + user.getGender() + "doesn't exist");
        }
        this.birthDate = user.getBirthDate();
    }
}
