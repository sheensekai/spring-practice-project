package com.example.entities;

import com.example.model.UserInfoModel;
import lombok.*;

import javax.persistence.*;

@Data
@NoArgsConstructor
@RequiredArgsConstructor
@Entity
@Table(name = "userinfo")
public class UserInfo {

    @Id
    private int userId;

    @NonNull
    @Column(name = "firstname")
    private String firstName;

    @NonNull
    @Column(name = "lastname")
    private String lastName;

    @NonNull
    @Column(name = "genderid")
    private int genderId;

    @NonNull
    @Column(name = "birthdate")
    private long birthDate;

    public UserInfo(UserInfoModel userInfo, int genderId) {
        this(userInfo.getFirstName(), userInfo.getLastName(), genderId, userInfo.getBirthDate());
    }

}
