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

    private int statusId;
    private long updatetime;

    private void userModelInit(int userId, int statusId, long updatetime) {
        this.userId = userId;
        this.statusId = statusId;
        this.updatetime = updatetime;
    }

    public UserModel(User user) {
        this(user.getUserName(), user.getEmail(), user.getPasswordHash());
        this.userModelInit(user.getUserId(), user.getStatusId(), user.getUpdatetime());
    }

    public UserModel(UserDTO userDTO) {
        this(userDTO.getUserName(), userDTO.getEmail(), userDTO.getPasswordHash());
        this.userModelInit(userDTO.getUserId(), userDTO.getUserId(), userDTO.getPasswordHash());
    }
}
