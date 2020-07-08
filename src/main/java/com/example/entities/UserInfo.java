package com.example.entities;

import com.example.model.UserInfoModel;
import lombok.*;

import javax.persistence.*;

@Data
@NoArgsConstructor
@RequiredArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "UserInfo")
public class UserInfo {

    @Id
    private int userId;

    @NonNull
    @Column(name = "FirstName")
    private String firstName;

    @NonNull
    @Column(name = "LastName")
    private String lastName;

    @NonNull
    @Column(name = "GenderId")
    private int genderId;

    @NonNull
    @Column(name = "BirthDate")
    private long birthDate;

    public UserInfo(UserInfoModel userInfo, int genderId) {
        this(userInfo.getFirstName(), userInfo.getLastName(), genderId, userInfo.getBirthDate());
    }

}
