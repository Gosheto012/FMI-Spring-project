package com.example.football.services;

import com.example.football.dto.TeamDTO;
import com.example.football.exceptions.DuplicateEntityException;
import com.example.football.exceptions.EntityNotFoundException;
import com.example.football.models.Group;
import com.example.football.models.Team;
import com.example.football.models.TeamStatus;
import com.example.football.models.Tournament;
import com.example.football.models.ids.GroupId;
import com.example.football.repositories.GroupRepository;
import com.example.football.repositories.TeamRepository;
import java.util.List;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class TeamServiceImpl implements TeamService {

    private static final Logger logger = LoggerFactory.getLogger(TeamServiceImpl.class);

    private TeamRepository teamRepository;

    private TournamentService tournamentService;

    private GroupService groupService;

    @Autowired
    public void setTeamRepository(TeamRepository teamRepository) {
        this.teamRepository = teamRepository;
    }

    @Autowired
    public void setTournamentService(TournamentService tournamentService) {
        this.tournamentService = tournamentService;
    }

    @Autowired
    public void setGroupService(GroupService groupService) {
        this.groupService = groupService;
    }

    @Override
    @Transactional
    public List<Team> getAllTeams(Long tournamentId) {
        tournamentService.getTournamentById(tournamentId);
        List<Team> teams = teamRepository.findAllByTournament_Id(tournamentId);
        if (teams.isEmpty()) {
            logger.info("There aren't any teams in this tournament");
        }
        return teams;
    }

    @Override
    @Transactional
    public List<Team> getAllTeamsByGroup(Long tournamentId, String groupName) {
        tournamentService.getTournamentById(tournamentId);
        groupService.getGroupById(tournamentId, groupName);
        List<Team> teams = teamRepository.findAllByTournament_IdAndGroupName(tournamentId, groupName);
        if (teams.isEmpty()) {
            logger.info("There aren't any teams in the given group");
        }
        return teams;
    }

    @Override
    @Transactional
    public Team getTeamById(Long tournamentId, Long teamId) {
        tournamentService.getTournamentById(tournamentId);
        Optional<Team> team = teamRepository.findByTournamentIdAndId(tournamentId, teamId);
        if (team.isEmpty()) {
            throw new EntityNotFoundException("team");
        }
        return team.get();
    }

    // Ograniucheniq za bazata edno unikalno ime na otbor za turnir
    @Override
    @Transactional
    public Team getTeamByName(Long tournamentId, String teamName) {
        tournamentService.getTournamentById(tournamentId);
        Optional<Team> team = teamRepository.findByTournamentIdAndTeamName(tournamentId, teamName);
        if (team.isEmpty()) {
            throw new EntityNotFoundException("team");
        }
        return team.get();
    }


    /*
     VALIDACIQ CHRE GRUPATA SUSHTESTVUVA?
     */
    @Override
    @Transactional
    public Team createTeam(Long tournamentId, TeamDTO teamDTO) {
        Tournament tournament = tournamentService.getTournamentById(tournamentId);
        String teamName = teamDTO.getTeamName();
        if (teamRepository.findByTournamentIdAndTeamName(tournamentId, teamName).isPresent()) {
            throw new DuplicateEntityException("team");
        }

        Team team = new Team();
        TeamStatus teamStatus = teamDTO.getTeamStatus();
        if (teamStatus == TeamStatus.GROUP) {
            String groupName = teamDTO.getGroupName();
            groupService.getGroupById(tournamentId, groupName);
            team.setGroupName(groupName);
        }

        team.setTeamName(teamName);
        team.setTeamStatus(teamDTO.getTeamStatus());
        team.setTournament(tournament);
        return teamRepository.saveAndFlush(team);
    }

    @Override
    @Transactional
    public Team updateTeam(Long tournamentId, Long teamId, TeamDTO teamDTO) {
        tournamentService.getTournamentById(tournamentId);
        String teamName = teamDTO.getTeamName();
        Optional<Team> possibleNameConflictTeam = teamRepository.findByTournamentIdAndTeamName(tournamentId, teamName);
        Team team = getTeamById(tournamentId, teamId);
        if (!team.getTeamName().equals(teamName) && possibleNameConflictTeam.isPresent()) {
            throw new DuplicateEntityException("team");
        }
        team.setTeamName(teamDTO.getTeamName());
        team.setGroupName(teamDTO.getGroupName());
        TeamStatus teamStatus = teamDTO.getTeamStatus();
        team.setTeamStatus(teamDTO.getTeamStatus());
        if (teamStatus == TeamStatus.GROUP) {
            String groupName = teamDTO.getGroupName();
            groupService.getGroupById(tournamentId, groupName);
            team.setGroupName(groupName);
        }
        return teamRepository.saveAndFlush(team);
    }

    @Override
    @Transactional
    public void deleteTeamById(Long tournamentId, Long teamId) {
        logger.info("Deleting team with tournament id = {} and team id = {}", tournamentId, teamId);
        Team team = getTeamById(tournamentId, teamId);
        teamRepository.delete(team);
    }

    @Override
    @Transactional
    public boolean existsByTournamentAndName(Long tournamentId, String teamName) {
        return teamRepository.findByTournamentIdAndTeamName(tournamentId, teamName).isPresent();
    }

    @Override
    @Transactional
    public boolean areInTheSameGroup(Long tournamentId, String firstTeamName, String secondTeamName) {
        Optional<Team> possibleFirstTeam = teamRepository.findByTournamentIdAndTeamName(tournamentId, firstTeamName);
        Optional<Team> possibleSecondTeam = teamRepository.findByTournamentIdAndTeamName(tournamentId, secondTeamName);
        if (possibleFirstTeam.isEmpty() || possibleSecondTeam.isEmpty()) {
            throw new EntityNotFoundException("team");
        }
        Team firstTeam = possibleFirstTeam.get();
        Team secondTeam = possibleSecondTeam.get();
        return firstTeam.getGroupName().equals(secondTeam.getGroupName());
    }
}
