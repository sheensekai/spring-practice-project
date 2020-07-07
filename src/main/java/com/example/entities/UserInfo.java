package com.example.entities;

import com.example.model.UserInfoModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "userinfo")
public class UserInfo {

    @Id
    private int userId;

    @Column(name = "firstname")
    private String firstName;

    @Column(name = "lastname")
    private String lastName;

    @Column(name = "genderid")
    private int genderId;

    @Column(name = "birthdate")
    private long birthDate;

    public UserInfo(String firstName, String lastName, long birthDate) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthDate = birthDate;
    }

    public UserInfo(UserInfoModel userInfo) {
        this.firstName = userInfo.getFirstName();
        this.lastName = userInfo.getLastName();
        this.birthDate = userInfo.getBirthDate();
    }

    public UserInfo(UserInfoModel userInfo, int genderId) {
        this(userInfo);
        this.genderId = genderId;
    }

}
