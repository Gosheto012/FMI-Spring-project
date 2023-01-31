package com.example.football.services;

import com.example.football.dto.TeamDTO;
import com.example.football.models.Team;
import java.util.List;
import javax.validation.Valid;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

public interface TeamService {

    List<Team> getAllTeams(Long tournamentId);

    List<Team> getAllTeamsByGroup(Long tournamentId, String groupName);

    Team getTeamById(Long tournamentId, Long teamId);

    Team getTeamByName(Long tournamentId, String teamName);

    Team createTeam(Long tournamentId, TeamDTO teamDTO);

    Team updateTeam(Long tournamentId, Long teamId, TeamDTO teamDTO);

    void deleteTeamById(Long tournamentId, Long teamId);

    boolean existsByTournamentAndName(Long tournamentId, String teamName);

    boolean areInTheSameGroup(Long tournamentId, String firstTeam, String secondTeam);
}
