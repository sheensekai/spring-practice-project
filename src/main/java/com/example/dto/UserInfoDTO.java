package com.example.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserInfoDTO {

    private int userId;
    private String firstName;
    private String lastName;
    private String gender;
    private long birthDate;

}
