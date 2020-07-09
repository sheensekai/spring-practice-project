package com.example.entities;

import com.example.model.UserModel;
import lombok.*;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "system_users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int userId;

    @Column(unique = true)
    private String userName;

    @Column(unique = true)
    private String email;

    @Column
    private long passwordHash;

    @Column
    private int statusId;

    @Column
    private long updateTime;

    public User(UserModel user) {
        this(user.getUserId(), user.getUserName(), user.getEmail(),
                user.getPasswordHash(), user.getStatusId(), user.getUpdateTime());
    }

    public void updateStatus(UserStatus newUserStatus) {
        this.statusId = newUserStatus.statusId;
        this.updateTime = newUserStatus.updateTime;
    }

}
