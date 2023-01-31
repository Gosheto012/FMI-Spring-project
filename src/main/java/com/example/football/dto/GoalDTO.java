package com.example.football.dto;

import java.util.Objects;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

public class GoalDTO {

    @NotNull
    private Long playerId;

    @NotNull
    @Min(value = 1L, message = "The minute of the scored goal must be between 1st and 120th min")
    @Max(value = 120L, message = "The minute of the scored goal must be between 1st and 120th min")
    private Long minuteScored;

    public Long getPlayerId() {
        return playerId;
    }

    public void setPlayerId(Long playerId) {
        this.playerId = playerId;
    }

    public Long getMinuteScored() {
        return minuteScored;
    }

    public void setMinuteScored(Long minuteScored) {
        this.minuteScored = minuteScored;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        GoalDTO goalDTO = (GoalDTO) o;
        return Objects.equals(playerId, goalDTO.playerId) && Objects.equals(minuteScored, goalDTO.minuteScored);
    }

    @Override
    public int hashCode() {
        return Objects.hash(playerId, minuteScored);
    }
}
