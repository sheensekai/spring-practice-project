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

    public UserInfo(UserInfoModel userInfo, int genderId) {
        this.firstName = userInfo.getFirstName();
        this.lastName = userInfo.getLastName();
        this.birthDate = userInfo.getBirthDate();
        this.genderId = genderId;
    }

}
