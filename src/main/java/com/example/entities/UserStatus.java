package com.example.entities;

import lombok.*;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table
public class UserStatus {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long userStatusUpdateId;

    @Column
    long updateTime;

    @Column
    int userId;

    @Column
    int statusId;

    public UserStatus(long updateTime, int userId, int statusId) {
        this.updateTime = updateTime;
        this.userId = userId;
        this.statusId = statusId;
    }
}
