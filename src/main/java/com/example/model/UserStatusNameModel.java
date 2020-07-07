package com.example.model;

import com.example.entities.UserStatusName;
import lombok.Data;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Data
public class UserStatusNameModel {
    private int statusId;

    @NonNull
    private String statusName;

    public UserStatusNameModel(UserStatusName userStatusName) {
        this(userStatusName.getStatusName());
        this.statusId = userStatusName.getStatusId();
    }
}
