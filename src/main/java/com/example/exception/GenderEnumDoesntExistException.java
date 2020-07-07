package com.example.exception;

public class GenderEnumDoesntExistException extends RuntimeException {
    public GenderEnumDoesntExistException(String message) {
        super(message);
    }
}
