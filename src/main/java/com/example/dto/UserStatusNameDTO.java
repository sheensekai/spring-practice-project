package com.example.dto;

import com.example.model.UserStatusNameModel;
import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class UserStatusNameDTO {
    private int statusId;

    private String statusName;

    public UserStatusNameDTO(UserStatusNameModel userStatusNameModel) {
        this(userStatusNameModel.getStatusId(), userStatusNameModel.getStatusName());
    }
}
