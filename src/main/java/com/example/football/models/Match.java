package com.example.football.models;

import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "match", schema = "football")
public class Match {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "first_team_name")
    private String firstTeamName;

    @Column(name = "second_team_name")
    private String secondTeamName;

    @Column(name = "first_team_scored_goals")
    private Long firstTeamScoredGoals;

    @Column(name = "second_team_scored_goals")
    private Long secondTeamScoredGoals;

    @Column(name = "is_played")
    private Boolean isPlayed;

    @Column(name = "is_group_stage_match")
    private Boolean isGroupStageMatch;

    @ManyToOne
    @JoinColumn(name = "tournament_id", nullable = false)
    private Tournament tournament;

    public Match() {}

    public Match(Long id, String firstTeamName, String secondTeamName, Long firstTeamScoredGoals, Long secondTeamScoredGoals,
                 Boolean isPlayed, Boolean isGroupStageMatch, Tournament tournament) {
        this.id = id;
        this.firstTeamName = firstTeamName;
        this.secondTeamName = secondTeamName;
        this.firstTeamScoredGoals = firstTeamScoredGoals;
        this.secondTeamScoredGoals = secondTeamScoredGoals;
        this.isPlayed = isPlayed;
        this.isGroupStageMatch = isGroupStageMatch;
        this.tournament = tournament;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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

    public Long getFirstTeamScoredGoals() {
        return firstTeamScoredGoals;
    }

    public void setFirstTeamScoredGoals(Long firstTeamScoredGoals) {
        this.firstTeamScoredGoals = firstTeamScoredGoals;
    }

    public Long getSecondTeamScoredGoals() {
        return secondTeamScoredGoals;
    }

    public void setSecondTeamScoredGoals(Long secondTeamScoredGoals) {
        this.secondTeamScoredGoals = secondTeamScoredGoals;
    }

    public Boolean getPlayed() {
        return isPlayed;
    }

    public void setPlayed(Boolean played) {
        isPlayed = played;
    }

    public Boolean getGroupStageMatch() {
        return isGroupStageMatch;
    }

    public void setGroupStageMatch(Boolean groupStageMatch) {
        isGroupStageMatch = groupStageMatch;
    }

    public Tournament getTournament() {
        return tournament;
    }

    public void setTournament(Tournament tournament) {
        this.tournament = tournament;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Match match = (Match) o;
        return Objects.equals(id, match.id) && Objects.equals(firstTeamName, match.firstTeamName) &&
            Objects.equals(secondTeamName, match.secondTeamName) &&
            Objects.equals(firstTeamScoredGoals, match.firstTeamScoredGoals) &&
            Objects.equals(secondTeamScoredGoals, match.secondTeamScoredGoals) &&
            Objects.equals(isPlayed, match.isPlayed) && Objects.equals(isGroupStageMatch, match.isGroupStageMatch) &&
            Objects.equals(tournament, match.tournament);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, firstTeamName, secondTeamName, firstTeamScoredGoals, secondTeamScoredGoals, isPlayed, isGroupStageMatch,
            tournament);
    }
}
