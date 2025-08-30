package com.example.foodcourt.domain.exception;

public class DocumentAlreadyExistsException extends RuntimeException {
    private static final String DEFAULT_MESSAGE = "El documento ya está registrado";

    public DocumentAlreadyExistsException() {
        super(DEFAULT_MESSAGE);
    }
}