package com.example.entities;

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
}
