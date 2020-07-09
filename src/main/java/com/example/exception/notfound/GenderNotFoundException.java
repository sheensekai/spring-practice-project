package com.example.exception.notfound;

public class GenderNotFoundException extends ResourceNotFoundException {
    public GenderNotFoundException(String message) {
        super(message);
    }
}
