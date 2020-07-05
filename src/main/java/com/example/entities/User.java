package com.example.entities;

import javax.persistence.*;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "username")
    private String userName;

    @Column(name = "email")
    private String email;
    private long passwordHash;

    public User() {
    }

    public User(long id, String userName, String email, long passwordHash) {
        this.id = id;
        this.userName = userName;
        this.email = email;
        this.passwordHash = passwordHash;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public long getPasswordHash() {
        return passwordHash;
    }

    public void setPasswordHash(long passwordHash) {
        this.passwordHash = passwordHash;
    }
}
