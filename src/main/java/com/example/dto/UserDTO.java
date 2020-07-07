package com.example.dto;

import com.example.model.UserModel;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserDTO {
    private String userName;
    private String email;
    private long passwordHash;

    public UserDTO(UserModel user) {
        this.userName = user.getUserName();
        this.email = user.getEmail();
        this.passwordHash = user.getPasswordHash();
    }
}
