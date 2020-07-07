package com.example.entities;

import com.example.model.UserModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(name = "username", unique = true)
    private String userName;

    @Column(name = "email", unique = true)
    private String email;

    @Column(name = "passwordhash")
    private long passwordHash;

    public User(UserModel user) {
        this.userName = user.getUserName();
        this.email = user.getEmail();
        this.passwordHash = user.getPasswordHash();
    }

}
