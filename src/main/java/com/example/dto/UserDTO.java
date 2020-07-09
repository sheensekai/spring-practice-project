package com.example.dto;

import com.example.model.UserModel;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {
    private int userId;

    private String userName;

    private String email;

    private long passwordHash;

    private int statusId;
    private long updateTime;

    public UserDTO(String userName, String email, long passwordHash) {
        this.userName = userName;
        this.email = email;
        this.passwordHash = passwordHash;
    }

    public UserDTO(UserModel userModel) {
        this(userModel.getUserId(), userModel.getUserName(), userModel.getEmail(), userModel.getPasswordHash(),
                userModel.getStatusId(), userModel.getUpdateTime());
    }
}
