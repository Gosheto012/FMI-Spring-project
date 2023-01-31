package com.example.football.exceptions;

public class DuplicateEntityException extends RuntimeException {
    public DuplicateEntityException(String errorMessage) {
        super(errorMessage);
    }
}
