package com.example.model;

import com.example.dto.UserDTO;
import com.example.entities.User;
import lombok.Data;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class UserModel {
    private int userId;

    @NonNull
    private String userName;

    @NonNull
    private String email;

    @NonNull
    private long passwordHash;

    public UserModel(User user) {
        this(user.getUserName(), user.getEmail(), user.getPasswordHash());
        this.userId = user.getUserId();
    }

    public UserModel(UserDTO userDTO) {
        this(userDTO.getUserName(), userDTO.getEmail(), userDTO.getPasswordHash());
        this.userId = userDTO.getUserId();
    }
}
