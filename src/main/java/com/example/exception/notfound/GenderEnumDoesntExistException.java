package com.example.exception.notfound;

public class GenderEnumDoesntExistException extends ResourceNotFoundException {
    public GenderEnumDoesntExistException(String message) {
        super(message);
    }
}
