package com.example.entities;

import com.example.model.UserStatusNameModel;
import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@RequiredArgsConstructor
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "UserStatusName")
public class UserStatusName {

    @Id
    private int statusId;

    @NonNull
    @Column
    private String statusName;

    public UserStatusName(UserStatusNameModel userStatusNameModel) {
        this(userStatusNameModel.getStatusId(), userStatusNameModel.getStatusName());
    }
}
