package com.example.football.exceptions;

public class PlayerNotInTheMatchException extends RuntimeException {
    public PlayerNotInTheMatchException(String errorMessage) {
        super(errorMessage);
    }
}
