package com.example.exception.exists;

public class UserStatusAlreadyExistsException extends ResourceAlreadyExistsException {
    public UserStatusAlreadyExistsException(String message) {
        super(message);
    }
}
