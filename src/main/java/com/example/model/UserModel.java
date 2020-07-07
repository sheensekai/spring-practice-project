package com.example.model;

import com.example.dto.UserDTO;
import com.example.entities.User;
import lombok.Data;

@Data
public class UserModel {
    private int userId;
    private String userName;
    private String email;
    private long passwordHash;

    public UserModel(User user) {
        this.userName = user.getUserName();
        this.email = user.getEmail();
        this.passwordHash = user.getPasswordHash();
    }

    public UserModel(UserDTO user) {
        this.userName = user.getUserName();
        this.email = user.getEmail();
        this.passwordHash = user.getPasswordHash();
    }
}
