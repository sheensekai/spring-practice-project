package com.example.dto;

import com.example.model.UserModel;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UserDTO {
    private String userName;
    private String email;
    private long passwordHash;

    public UserDTO(String userName, String email, long passwordHash) {
        this.userName = userName;
        this.email = email;
        this.passwordHash = passwordHash;
    }

    public UserDTO(UserModel user) {
        this.userName = user.getUserName();
        this.email = user.getEmail();
        this.passwordHash = user.getPasswordHash();
    }
}
