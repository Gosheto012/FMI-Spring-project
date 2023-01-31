package com.example.football.exceptions;

public class TeamInMatchNotChangeNamesException extends RuntimeException {
    public TeamInMatchNotChangeNamesException(String errorMessage) {
        super(errorMessage);
    }
}
