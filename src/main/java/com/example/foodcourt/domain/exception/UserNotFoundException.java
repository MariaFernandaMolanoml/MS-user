package com.example.foodcourt.domain.exception;

public class UserNotFoundException extends RuntimeException {
    private static final String DEFAULT_MESSAGE = "El usuario no fue encontrado";

    public UserNotFoundException() {
        super(DEFAULT_MESSAGE);
    }
}