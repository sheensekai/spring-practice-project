package com.example.model;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UserModel {
    private String userName;
    private String email;
    private long passwordHash;

    public UserModel(String userName, String email, long passwordHash) {
        this.userName = userName;
        this.email = email;
        this.passwordHash = passwordHash;
    }
}
