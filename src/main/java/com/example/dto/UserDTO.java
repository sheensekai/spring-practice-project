package com.example.dto;

import com.example.entities.User;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UserDTO {
    private long id;
    private String userName;
    private String email;
    private long passwordHash;

    public UserDTO(long id, String userName, String email, long passwordHash) {
        this.id = id;
        this.userName = userName;
        this.email = email;
        this.passwordHash = passwordHash;
    }
}
