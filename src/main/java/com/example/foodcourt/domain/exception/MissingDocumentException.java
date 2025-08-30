package com.example.foodcourt.domain.exception;

public class MissingDocumentException extends RuntimeException {
    public MissingDocumentException() {
        super("Document is mandatory");
    }
}
