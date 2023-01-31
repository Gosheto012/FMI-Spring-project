package com.example.football.controllers;

import com.example.football.dto.GroupDTO;
import com.example.football.dto.TournamentDTO;
import com.example.football.models.Group;
import com.example.football.models.Tournament;
import com.example.football.services.GroupService;
import java.util.List;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/tournaments/{tournamentId}/groups")
public class GroupController {

    private GroupService groupService;

    @Autowired
    public void setGroupService(GroupService groupService) {
        this.groupService = groupService;
    }

    @GetMapping
    public List<Group> getAllGroups(@PathVariable Long tournamentId) {
        return groupService.getAllGroups(tournamentId);
    }

    @GetMapping(value = "/{groupName}")
    public Group getGroupById(@PathVariable Long tournamentId, @PathVariable String groupName) {
        return groupService.getGroupById(tournamentId, groupName);
    }

    @PostMapping
    public Group createGroup(@PathVariable Long tournamentId, @RequestBody @Valid GroupDTO groupDTO) {
        return groupService.createGroup(tournamentId, groupDTO);
    }

    @PutMapping(value = "/{groupName}")
    public Group updateGroup(@PathVariable Long tournamentId, @PathVariable String groupName, @RequestBody @Valid GroupDTO groupDTO) {
        return groupService.updateGroup(tournamentId, groupName, groupDTO);
    }

    @DeleteMapping(value = "/{groupName}")
    public void deleteGroupById(@PathVariable Long tournamentId, @PathVariable String groupName) {
        groupService.deleteGroupById(tournamentId, groupName);
    }
}
