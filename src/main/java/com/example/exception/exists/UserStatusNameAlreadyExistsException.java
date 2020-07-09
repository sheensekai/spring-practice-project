package com.example.exception.exists;

public class UserStatusNameAlreadyExistsException extends ResourceAlreadyExistsException {
    public UserStatusNameAlreadyExistsException(String message) {
        super(message);
    }
}
