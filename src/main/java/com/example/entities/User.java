package com.example.entities;

import com.example.model.UserModel;
import lombok.*;

import javax.persistence.*;

@Data
@NoArgsConstructor
@RequiredArgsConstructor
@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int userId;

    @NonNull
    @Column(name = "username", unique = true)
    private String userName;

    @NonNull
    @Column(name = "email", unique = true)
    private String email;

    @NonNull
    @Column(name = "passwordhash")
    private long passwordHash;

    public User(UserModel user) {
        this(user.getUserName(), user.getEmail(), user.getPasswordHash());
    }

}
