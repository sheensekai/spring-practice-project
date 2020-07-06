package com.example.entities;

import com.example.model.UserModel;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
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

    public UserInfo(String firstName, String lastName, int genderId, long birthDate) {
        this(firstName, lastName, birthDate);
        this.genderId = genderId;
    }

}
