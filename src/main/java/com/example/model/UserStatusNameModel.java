package com.example.model;

import com.example.dto.UserStatusNameDTO;
import com.example.entities.UserStatusName;
import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class UserStatusNameModel {
    private int statusId;

    private String statusName;

    public UserStatusNameModel(UserStatusName userStatusName) {
        this(userStatusName.getStatusId(), userStatusName.getStatusName());
    }

    public UserStatusNameModel(UserStatusNameDTO userStatusNameDTO) {
        this(userStatusNameDTO.getStatusId(), userStatusNameDTO.getStatusName());
    }
}
