package com.example.foodcourt.domain.exception;

public class MissingEmailException extends RuntimeException {
    public MissingEmailException() {
        super("Email is mandatory");
    }
}