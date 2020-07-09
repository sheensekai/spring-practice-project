package com.example.entities;

import com.example.model.UserStatusNameModel;
import lombok.*;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table
public class UserStatusName {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int statusId;

    @Column
    private String statusName;

    public UserStatusName(UserStatusNameModel userStatusNameModel) {
        this(userStatusNameModel.getStatusId(), userStatusNameModel.getStatusName());
    }
}
