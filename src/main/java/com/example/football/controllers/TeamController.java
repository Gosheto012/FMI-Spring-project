package com.example.football.controllers;

import com.example.football.dto.GroupDTO;
import com.example.football.dto.TeamDTO;
import com.example.football.models.Group;
import com.example.football.models.Team;
import com.example.football.services.TeamService;
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
@RequestMapping("/api/tournaments/{tournamentId}/teams")
public class TeamController {

    private TeamService teamService;

    @Autowired
    public void setTeamService(TeamService teamService) {
        this.teamService = teamService;
    }

    @PostMapping
    public Team createTeam(@PathVariable Long tournamentId, @RequestBody @Valid TeamDTO teamDTO) {
        return teamService.createTeam(tournamentId, teamDTO);
    }

    @GetMapping
    public List<Team> getAllTeams(@PathVariable Long tournamentId) {
        return teamService.getAllTeams(tournamentId);
    }

    @GetMapping(path = "/groupTeams/{groupName}")
    public List<Team> getAllTeamsByGroup(@PathVariable Long tournamentId, @PathVariable String groupName) {
        return teamService.getAllTeamsByGroup(tournamentId, groupName);
    }

    @GetMapping(value = "/{teamId}")
    public Team getTeamById(@PathVariable Long tournamentId, @PathVariable Long teamId) {
        return teamService.getTeamById(tournamentId, teamId);
    }

    @PutMapping(value = "/{teamId}")
    public Team updateTeam(@PathVariable Long tournamentId, @PathVariable Long teamId, @RequestBody @Valid TeamDTO teamDTO) {
        return teamService.updateTeam(tournamentId, teamId, teamDTO);
    }

    @DeleteMapping(value = "/{teamId}")
    public void deleteTeamById(@PathVariable Long tournamentId, @PathVariable Long teamId) {
        teamService.deleteTeamById(tournamentId, teamId);
    }
}
