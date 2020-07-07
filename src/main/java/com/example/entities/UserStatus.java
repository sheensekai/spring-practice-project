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
@Table(name = "user_statistics")
public class UserStatus {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    long userStatusUpdateId;

    @NonNull
    @Column(name = "updateTime")
    long updateTime;

    @NonNull
    @Column(name = "userid")
    int userId;

    @NonNull
    @Column(name = "statusid")
    int statusId;
}
