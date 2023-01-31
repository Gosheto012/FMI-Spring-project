package com.example.football.models;

import com.example.football.models.ids.GroupId;

import java.util.Objects;
import javax.persistence.*;

@Entity
@Table(name = "group", schema = "football")
public class Group {

    @EmbeddedId
    private GroupId groupId;

    @Column(name = "is_finished")
    private Boolean isFinished;

    public GroupId getGroupId() {
        return groupId;
    }

    @ManyToOne
    @JoinColumn(name = "tournament_id", nullable = false)
    private Tournament tournament;

    public void setGroupId(GroupId groupId) {
        this.groupId = groupId;
    }

    public Boolean isFinished() {
        return isFinished;
    }



    public void setIsFinished(Boolean finished) {
        isFinished = finished;
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
        Group group = (Group) o;
        return isFinished == group.isFinished && groupId.equals(group.groupId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(groupId, isFinished);
    }
}
