package com.example.football.models;

import java.io.Serializable;
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
@Table(name = "goal", schema = "football")
public class Goal  {

    @Column
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "player_id")
    private Long playerId;

    @Column(name = "minute_scored")
    private Long minuteScored;

    @ManyToOne
    @JoinColumn(name = "match_id", nullable = false)
    private Match match;

    public Goal() {}

    public Goal(Long id, Long playerId, Long minuteScored, Match match) {
        this.id = id;
        this.playerId = playerId;
        this.minuteScored = minuteScored;
        this.match = match;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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

    public Match getMatch() {
        return match;
    }

    public void setMatch(Match match) {
        this.match = match;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Goal goal = (Goal) o;
        return Objects.equals(id, goal.id) && Objects.equals(playerId, goal.playerId) &&
            Objects.equals(minuteScored, goal.minuteScored) && Objects.equals(match, goal.match);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, playerId, minuteScored, match);
    }
}
