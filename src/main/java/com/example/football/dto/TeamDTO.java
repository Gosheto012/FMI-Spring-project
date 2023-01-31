package com.example.football.dto;

import com.example.football.models.TeamStatus;
import java.util.Objects;

public class TeamDTO {

    private String teamName;

    private TeamStatus teamStatus;

    private String groupName;

    public TeamDTO() {}

    public TeamDTO(String teamName, TeamStatus teamStatus, String groupName) {
        this.teamName = teamName;
        this.teamStatus = teamStatus;
        this.groupName = groupName;
    }

    public String getTeamName() {
        return teamName;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    public TeamStatus getTeamStatus() {
        return teamStatus;
    }

    public void setTeamStatus(TeamStatus teamStatus) {
        this.teamStatus = teamStatus;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        TeamDTO teamDTO = (TeamDTO) o;
        return Objects.equals(teamName, teamDTO.teamName) && teamStatus == teamDTO.teamStatus &&
            Objects.equals(groupName, teamDTO.groupName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(teamName, teamStatus, groupName);
    }
}
