package com.example.dto;

import com.example.entities.User;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UserDTO {
    private int id;
    private String userName;
    private String email;
    private long passwordHash;

    public UserDTO(int id, String userName, String email, long passwordHash) {
        this.id = id;
        this.userName = userName;
        this.email = email;
        this.passwordHash = passwordHash;
    }
}
