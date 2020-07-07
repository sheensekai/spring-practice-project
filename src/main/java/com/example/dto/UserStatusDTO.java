package com.example.dto;

import com.example.model.UserStatusModel;
import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class UserStatusDTO {
    private long userStatusUpdateId;
    private Integer userId;
    private String onlineStatus;
    private Long updateTime;

    public UserStatusDTO(UserStatusModel userStatusModel) {
        this(userStatusModel.getUserStatusUpdateId(), userStatusModel.getUserId(),
                userStatusModel.getOnlineStatus().toString().toLowerCase(), userStatusModel.getUpdateTime());
    }
}
