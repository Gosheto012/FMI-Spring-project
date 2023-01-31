package com.example.football.exceptions;

public class TeamsNotInTheSameGroupException extends RuntimeException {

    private final String firstTeamName;

    private final String secondTeamName;

    public TeamsNotInTheSameGroupException(String firstTeamName, String secondTeamName) {
        super();
        this.firstTeamName = firstTeamName;
        this.secondTeamName = secondTeamName;
    }

    public String getFirstTeamName() {
        return firstTeamName;
    }

    public String getSecondTeamName() {
        return secondTeamName;
    }
}
