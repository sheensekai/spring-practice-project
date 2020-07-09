package com.example.exception.notfound;

public class UserStatusNotFoundException extends ResourceNotFoundException {
    public UserStatusNotFoundException(String message) {
        super(message);
    }
}
