package com.example.football.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Objects;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;

public class MatchDTO {

    @NotEmpty
    private String firstTeamName;

    @NotEmpty
    private String secondTeamName;

    @Min(value = 0)
    private Long firstTeamScoredGoals;

    @Min(value = 0)
    private Long secondTeamScoredGoals;
    
    private Boolean isPlayed;

    private Boolean isGroupStageMatch;

    public String getFirstTeamName() {
        return firstTeamName;
    }

    public void setFirstTeamName(String firstTeamName) {
        this.firstTeamName = firstTeamName;
    }

    public String getSecondTeamName() {
        return secondTeamName;
    }

    public void setSecondTeamName(String secondTeamName) {
        this.secondTeamName = secondTeamName;
    }

    @Min(value = 0)
    public Long getFirstTeamScoredGoals() {
        return firstTeamScoredGoals;
    }

    public void setFirstTeamScoredGoals(Long firstTeamScoredGoals) {
        this.firstTeamScoredGoals = firstTeamScoredGoals;
    }

    @Min(value = 0)
    public Long getSecondTeamScoredGoals() {
        return secondTeamScoredGoals;
    }

    public void setSecondTeamScoredGoals(Long secondTeamScoredGoals) {
        this.secondTeamScoredGoals = secondTeamScoredGoals;
    }

    @JsonProperty("isPlayed")
    public Boolean isPlayed() {
        return isPlayed;
    }

    public void setPlayed(Boolean played) {
        isPlayed = played;
    }

    @JsonProperty("isGroupStageMatch")
    public Boolean isGroupStageMatch() {
        return isGroupStageMatch;
    }

    public void setGroupStageMatch(Boolean groupStageMatch) {
        isGroupStageMatch = groupStageMatch;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        MatchDTO matchDTO = (MatchDTO) o;
        return Objects.equals(firstTeamName, matchDTO.firstTeamName) &&
            Objects.equals(secondTeamName, matchDTO.secondTeamName) &&
            Objects.equals(firstTeamScoredGoals, matchDTO.firstTeamScoredGoals) &&
            Objects.equals(secondTeamScoredGoals, matchDTO.secondTeamScoredGoals) &&
            Objects.equals(isPlayed, matchDTO.isPlayed) && Objects.equals(isGroupStageMatch, matchDTO.isGroupStageMatch);
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstTeamName, secondTeamName, firstTeamScoredGoals, secondTeamScoredGoals, isPlayed, isGroupStageMatch);
    }
}
