package com.example.service;

import com.example.model.UserModel;

public interface UserService {
    UserModel addUser(UserModel userModel);
    UserModel updateUser(UserModel userModel);
    UserModel getUserByUserId(int userId);

    boolean existsByUserId (int userId);
    boolean containsByUserName(String userName);
    boolean containsByEmail(String email);
}
