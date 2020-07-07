package com.example.entities;

import com.example.model.UserModel;
import lombok.*;

import javax.persistence.*;

@Data
@NoArgsConstructor
@RequiredArgsConstructor
@Entity
@Table(name = "SystemUsers")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int userId;

    @NonNull
    @Column(name = "Username", unique = true)
    private String userName;

    @NonNull
    @Column(name = "Email", unique = true)
    private String email;

    @NonNull
    @Column(name = "PasswordHash")
    private long passwordHash;

    @Column(name = "OnlineStatusId")
    private int statusId;

    @Column(name = "OnlineStatusUpdateTime")
    private long updatetime;

    public User(UserModel user) {
        this(user.getUserName(), user.getEmail(), user.getPasswordHash());
    }

    public void updateStatus(UserStatus newUserStatus) {
        this.statusId = newUserStatus.statusId;
        this.updatetime = newUserStatus.updateTime;
    }

}
