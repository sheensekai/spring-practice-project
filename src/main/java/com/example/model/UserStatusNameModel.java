package com.example.model;

import com.example.dto.UserStatusNameDTO;
import com.example.entities.UserStatusName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@AllArgsConstructor
@Data
public class UserStatusNameModel {
    private int statusId;

    @NonNull
    private String statusName;

    public UserStatusNameModel(UserStatusName userStatusName) {
        this(userStatusName.getStatusId(), userStatusName.getStatusName());
    }

    public UserStatusNameModel(UserStatusNameDTO userStatusNameDTO) {
        this(userStatusNameDTO.getStatusId(), userStatusNameDTO.getStatusName());
    }
}
