package com.example.foodcourt.domain.exception;

public class RoleNotFoundException extends RuntimeException {
    private static final String DEFAULT_MESSAGE = "El rol especificado no existe";

    public RoleNotFoundException() {
        super(DEFAULT_MESSAGE);
    }
}