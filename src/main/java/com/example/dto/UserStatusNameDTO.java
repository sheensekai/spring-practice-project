package com.example.dto;

import com.example.model.UserStatusNameModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@AllArgsConstructor
@Data
public class UserStatusNameDTO {
    private int statusId;

    @NonNull
    private String statusName;

    public UserStatusNameDTO(UserStatusNameModel userStatusNameModel) {
        this(userStatusNameModel.getStatusId(), userStatusNameModel.getStatusName());
    }
}
