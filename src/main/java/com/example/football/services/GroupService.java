package com.example.football.services;

import com.example.football.dto.GroupDTO;
import com.example.football.models.Group;
import java.util.List;
import org.springframework.web.bind.annotation.PathVariable;

public interface GroupService {

    Group createGroup(Long tournamentId, GroupDTO groupDTO);

    List<Group> getAllGroups(Long tournamentId);

    Group getGroupById(Long tournamentId, String groupName);

    Group updateGroup(Long tournamentId, String groupName, GroupDTO groupDTO);

    void deleteGroupById(Long tournamentId, String groupName);
}
