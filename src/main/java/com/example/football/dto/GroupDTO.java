package com.example.football.dto;

import java.util.Objects;

public class GroupDTO {

    private String groupName;

    private Boolean isFinished;

    public GroupDTO() {}

    public GroupDTO(String groupName, Boolean isFinished) {
        this.groupName = groupName;
        this.isFinished = isFinished;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public Boolean getIsFinished() {
        return isFinished;
    }

    public void setIsFinished(Boolean finished) {
        isFinished = finished;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        GroupDTO groupDTO = (GroupDTO) o;
        return Objects.equals(groupName, groupDTO.groupName) && Objects.equals(isFinished, groupDTO.isFinished);
    }

    @Override
    public int hashCode() {
        return Objects.hash(groupName, isFinished);
    }
}
