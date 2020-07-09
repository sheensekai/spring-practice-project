package com.example.entities;

import com.example.model.UserInfoModel;
import lombok.*;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table
public class UserInfo {

    @Id
    private int userId;

    @Column
    private String firstName;

    @Column
    private String lastName;

    @Column
    private int genderId;

    @Column
    private long birthDate;

    public UserInfo(UserInfoModel userInfo, int genderId) {
        this(userInfo.getUserId(), userInfo.getFirstName(), userInfo.getLastName(), genderId, userInfo.getBirthDate());
    }

}
