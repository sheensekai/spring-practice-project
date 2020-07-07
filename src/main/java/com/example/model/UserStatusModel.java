package com.example.model;

import com.example.UserStatusEnum;
import com.example.entities.UserStatus;
import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class UserStatusModel {
    private long userStatusUpdateId;
    private Integer userId;
    private UserStatusEnum onlineStatus;
    private Long updateTime;

    public UserStatusModel(UserStatus userStatus, UserStatusEnum onlineStatus) {
        this(userStatus.getUserStatusUpdateId(), userStatus.getUserId(), onlineStatus, userStatus.getUpdateTime());
    }
}
