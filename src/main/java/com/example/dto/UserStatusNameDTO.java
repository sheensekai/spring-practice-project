package com.example.dto;

import lombok.Data;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Data
public class UserStatusNameDTO {
    private int statusId;

    @NonNull
    private String statusName;
}
