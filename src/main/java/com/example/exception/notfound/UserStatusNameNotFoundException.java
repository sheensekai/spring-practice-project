package com.example.exception.notfound;

public class UserStatusNameNotFoundException extends ResourceNotFoundException {
    public UserStatusNameNotFoundException(String message) {
        super(message);
    }
}
