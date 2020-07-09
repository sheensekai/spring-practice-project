package com.example.exception.exists;

public class UserInfoAlreadyExistsException extends ResourceAlreadyExistsException {
    public UserInfoAlreadyExistsException(String message) {
        super(message);
    }
}
