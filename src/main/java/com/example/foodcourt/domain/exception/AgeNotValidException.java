package com.example.foodcourt.domain.exception;

public class AgeNotValidException extends RuntimeException {
    private static final String DEFAULT_MESSAGE = "El usuario debe ser mayor de edad";

    public AgeNotValidException() {
        super(DEFAULT_MESSAGE);
    }
}
