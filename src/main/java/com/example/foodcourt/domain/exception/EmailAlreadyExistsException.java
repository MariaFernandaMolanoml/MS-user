package com.example.foodcourt.domain.exception;

public class EmailAlreadyExistsException extends RuntimeException {
    private static final String DEFAULT_MESSAGE = "El correo ya está registrado";

    public EmailAlreadyExistsException() {
        super(DEFAULT_MESSAGE);
    }
}
