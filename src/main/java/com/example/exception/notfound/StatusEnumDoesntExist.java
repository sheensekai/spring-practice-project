package com.example.exception.notfound;

public class StatusEnumDoesntExist extends ResourceNotFoundException {
    public StatusEnumDoesntExist(String message) {
        super(message);
    }
}
