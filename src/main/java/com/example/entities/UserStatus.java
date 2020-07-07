package com.example.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "UserStatistics")
public class UserStatus {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    long userStatusUpdateId;

    @NonNull
    @Column(name = "UpdateTime")
    long updateTime;

    @NonNull
    @Column(name = "UserId")
    int userId;

    @NonNull
    @Column(name = "StatusId")
    int statusId;
}
