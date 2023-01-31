package com.example.football.models;


import com.example.football.validators.ValidNumberOfGroups;

import java.util.Objects;
import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "tournament", schema = "football")
public class Tournament {

    public static final String GENERATOR_NAME = "sequenceGenerator";

    @Id
    @Column(name = "tournament_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "tournament_name", unique = true)
    @NotEmpty(message = "The name of the tournament shouldn't be empty")
    private String tournamentName;

    @Column(name = "number_groups")
    @NotNull
    @ValidNumberOfGroups
    @NotEmpty(message = "The number of groups shouldn't be empty")
    private Long numberOfGroups;

    @Column
    private String description;


    public Tournament() {}

    public Tournament(Long id, String tournamentName, Long numberOfGroups, String description) {
        this.id = id;
        this.tournamentName = tournamentName;
        this.numberOfGroups = numberOfGroups;
        this.description = description;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @NotEmpty(message = "The name of the tournament shouldn't be empty")
    public String getTournamentName() {
        return tournamentName;
    }

    public void setTournamentName(String tournamentName) {
        this.tournamentName = tournamentName;
    }

    @ValidNumberOfGroups
    public Long getNumberOfGroups() {
        return numberOfGroups;
    }

    public void setNumberOfGroups(Long numberOfGroups) {
        this.numberOfGroups = numberOfGroups;
    }

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
        Tournament that = (Tournament) o;
        return Objects.equals(id, that.id) && Objects.equals(tournamentName, that.tournamentName) &&
            Objects.equals(numberOfGroups, that.numberOfGroups) && Objects.equals(description, that.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, tournamentName, numberOfGroups, description);
    }
}
