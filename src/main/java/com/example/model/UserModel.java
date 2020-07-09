package com.example.model;

import com.example.dto.UserDTO;
import com.example.entities.User;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserModel {
    private int userId;
    private String userName;
    private String email;
    private long passwordHash;
    private int statusId;
    private long updateTime;

    public UserModel(String userName, String email, long passwordHash) {
        this.userName = userName;
        this.email = email;
        this.passwordHash = passwordHash;
    }

    public UserModel(User user) {
        this(user.getUserId(), user.getUserName(), user.getEmail(), user.getPasswordHash(), user.getStatusId(),
                user.getUpdateTime());
    }

    public UserModel(UserDTO userDTO) {
        this(userDTO.getUserId(), userDTO.getUserName(), userDTO.getEmail(), userDTO.getPasswordHash(),
                userDTO.getStatusId(), userDTO.getUpdateTime());
    }

}
