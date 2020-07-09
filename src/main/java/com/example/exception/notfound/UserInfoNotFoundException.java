package com.example.exception.notfound;

public class UserInfoNotFoundException extends ResourceNotFoundException {
    public UserInfoNotFoundException(String message) {
        super(message);
    }
}
