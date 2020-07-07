package com.example.dto;

import com.example.model.UserModel;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserDTO {
    private int userId;
    private String userName;
    private String email;
    private long passwordHash;

    public UserDTO(UserModel userModel) {
        this.userId = userModel.getUserId();
        this.userName = userModel.getUserName();
        this.email = userModel.getEmail();
        this.passwordHash = userModel.getPasswordHash();
    }
}
