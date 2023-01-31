package com.example.football.exceptions;

public class ExceededLimitException extends RuntimeException {
    public ExceededLimitException(String errorMessage) {
        super(errorMessage);
    }
}