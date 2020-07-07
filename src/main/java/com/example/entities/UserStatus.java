package com.example.entities;

import lombok.*;

import javax.persistence.*;

@Data
@RequiredArgsConstructor
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
