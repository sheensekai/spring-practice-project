package com.example.dto;

import com.example.GenderEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserInfoDTO {

    private int userId;
    private String firstName;
    private String lastName;
    private String gender;
    private long birthDate;

}
