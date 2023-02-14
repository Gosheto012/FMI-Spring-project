package com.example.football.dto;

import java.util.Objects;
import javax.validation.constraints.Min;

public class RankingDTO  implements Comparable<RankingDTO> {
    private String teamName;

    @Min(value = 0)
    private Long playedMatches;

    @Min(value = 0)
    private Long wins;

    @Min(value = 0)
    private Long draws;

    @Min(value = 0)
    private Long loses;

    @Min(value = 0)
    private Long goalsFor;

    @Min(value = 0)
    private Long goalsAgainst;

    private Long goalDifference;

    @Min(value = 0)
    private Long points;

    public RankingDTO() {}

    public RankingDTO(String teamName, Long playedMatches, Long wins, Long draws, Long loses, Long goalsFor,
                      Long goalsAgainst,
                      Long goalDifference, Long points) {
        this.teamName = teamName;
        this.playedMatches = playedMatches;
        this.wins = wins;
        this.draws = draws;
        this.loses = loses;
        this.goalsFor = goalsFor;
        this.goalsAgainst = goalsAgainst;
        this.goalDifference = goalDifference;
        this.points = points;
    }

    public String getTeamName() {
        return teamName;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    public Long getPlayedMatches() {
        return playedMatches;
    }

    public void setPlayedMatches(Long playedMatches) {
        this.playedMatches = playedMatches;
    }

    public Long getWins() {
        return wins;
    }

    public void setWins(Long wins) {
        this.wins = wins;
    }

    public Long getDraws() {
        return draws;
    }

    public void setDraws(Long draws) {
        this.draws = draws;
    }

    public Long getLoses() {
        return loses;
    }

    public void setLoses(Long loses) {
        this.loses = loses;
    }

    public Long getGoalsFor() {
        return goalsFor;
    }

    public void setGoalsFor(Long goalsFor) {
        this.goalsFor = goalsFor;
    }

    public Long getGoalsAgainst() {
        return goalsAgainst;
    }

    public void setGoalsAgainst(Long goalsAgainst) {
        this.goalsAgainst = goalsAgainst;
    }

    public Long getGoalDifference() {
        return goalDifference;
    }

    public void setGoalDifference(Long goalDifference) {
        this.goalDifference = goalDifference;
    }

    public Long getPoints() {
        return points;
    }

    public void setPoints(Long points) {
        this.points = points;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        RankingDTO that = (RankingDTO) o;
        return Objects.equals(teamName, that.teamName) &&
            Objects.equals(playedMatches, that.playedMatches) && Objects.equals(wins, that.wins) &&
            Objects.equals(draws, that.draws) && Objects.equals(loses, that.loses) &&
            Objects.equals(goalsFor, that.goalsFor) && Objects.equals(goalsAgainst, that.goalsAgainst) &&
            Objects.equals(goalDifference, that.goalDifference) && Objects.equals(points, that.points);
    }

    @Override
    public int hashCode() {
        return Objects.hash(teamName, playedMatches, wins, draws, loses, goalsFor, goalsAgainst, goalDifference, points);
    }

    @Override
    public int compareTo(RankingDTO other) {
        if (points > other.points) return 1;
        if (points < other.points) return -1;
        if (goalDifference > other.goalDifference) return  1;
        if (goalDifference < other.goalDifference) return -1;
        if (goalsFor > other.goalsFor) return 1;
        if (goalsFor < other.goalsFor) return -1;
        return other.teamName.compareTo(teamName);

    }
}
