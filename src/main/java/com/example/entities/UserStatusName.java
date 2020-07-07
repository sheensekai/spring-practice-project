package com.example.entities;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@RequiredArgsConstructor
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
