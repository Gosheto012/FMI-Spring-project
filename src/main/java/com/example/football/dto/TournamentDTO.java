package com.example.football.dto;

import com.example.football.validators.ValidNumberOfGroups;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Objects;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class TournamentDTO {

    @JsonProperty("tournamentName")
    @NotNull
    @NotEmpty(message = "The name of the tournament shouldn't be empty")
    private String tournamentName;

    @JsonProperty("numberOfGroups")
    @NotNull
    @NotEmpty(message = "The number of groups shouldn't be empty")
    @ValidNumberOfGroups
    private Long numberOfGroups;

    @JsonProperty("description")
    private String description;

    public TournamentDTO() {}

    public TournamentDTO(@JsonProperty("tournamentName") String tournamentName,
                         @JsonProperty("numberOfGroups") Long numberOfGroups,
                         @JsonProperty("description") String description) {
        this.tournamentName = tournamentName;
        this.numberOfGroups = numberOfGroups;
        this.description = description;
    }

    @JsonProperty("tournamentName")
    @NotNull(message = "The name of the tournament shouldn't be empty")
    public String getTournamentName() {
        return tournamentName;
    }

    public void setTournamentName(String tournamentName) {
        this.tournamentName = tournamentName;
    }

    @JsonProperty("numberOfGroups")
    @NotNull(message = "The number of groups shouldn't be empty")
    @ValidNumberOfGroups
    public Long getNumberOfGroups() {
        return numberOfGroups;
    }

    public void setNumberOfGroups(Long numberOfGroups) {
        this.numberOfGroups = numberOfGroups;
    }

    @JsonProperty("description")
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        TournamentDTO that = (TournamentDTO) o;
        return Objects.equals(tournamentName, that.tournamentName) && Objects.equals(numberOfGroups, that.numberOfGroups) &&
            Objects.equals(description, that.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(tournamentName, numberOfGroups, description);
    }
}
