package com.example.football.models.ids;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class GroupId implements Serializable {

    @Column(name = "group_name")
    private String groupName;

    public GroupId() {
    }

    public GroupId(String groupName) {
        this.groupName = groupName;
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
        GroupId groupId = (GroupId) o;
        return Objects.equals(groupName, groupId.groupName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(groupName);
    }
}
