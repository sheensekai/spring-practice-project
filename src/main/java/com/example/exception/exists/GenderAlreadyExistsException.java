package com.example.exception.exists;

public class GenderAlreadyExistsException extends ResourceAlreadyExistsException {
    public GenderAlreadyExistsException(String message) {
        super(message);
    }
}
