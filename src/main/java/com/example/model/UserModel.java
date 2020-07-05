package com.example.model;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UserModel {
    private long id;
    private String userName;
    private String email;
    private long passwordHash;

    public UserModel(long id, String userName, String email, long passwordHash) {
        this.id = id;
        this.userName = userName;
        this.email = email;
        this.passwordHash = passwordHash;
    }
}
