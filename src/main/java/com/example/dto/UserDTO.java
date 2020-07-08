package com.example.dto;

import com.example.model.UserModel;
import lombok.Data;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class UserDTO {
    private int userId;

    @NonNull
    private String userName;

    @NonNull
    private String email;

    @NonNull
    private long passwordHash;

    private int statusId;
    private long updateTime;

    private void userDTOInit(int userId, int statusId, long updateTime) {
        this.userId = userId;
        this.statusId = statusId;
        this.updateTime = updateTime;
    }

    public UserDTO(UserModel userModel) {
        this(userModel.getUserName(), userModel.getEmail(), userModel.getPasswordHash());
        this.userDTOInit(userModel.getUserId(), userModel.getStatusId(), userModel.getUpdatetime());
    }
}
