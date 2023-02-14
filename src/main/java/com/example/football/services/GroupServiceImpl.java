package com.example.football.services;

import com.example.football.dto.GroupDTO;
import com.example.football.dto.RankingDTO;
import com.example.football.exceptions.DuplicateEntityException;
import com.example.football.exceptions.EntityNotFoundException;
import com.example.football.exceptions.ExceededLimitException;
import com.example.football.models.Group;
import com.example.football.models.Match;
import com.example.football.models.Team;
import com.example.football.models.Tournament;
import com.example.football.models.ids.GroupId;
import com.example.football.repositories.GroupRepository;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class GroupServiceImpl implements GroupService {

    private static final Logger logger = LoggerFactory.getLogger(GroupServiceImpl.class);

    private GroupRepository groupRepository;

    private TournamentService tournamentService;

    private MatchService matchService;

    private TeamService teamService;

    @Autowired
    public void setGroupRepository(GroupRepository groupRepository) {
        this.groupRepository = groupRepository;
    }

    @Autowired
    public void setTournamentService(TournamentService tournamentService) {
        this.tournamentService = tournamentService;
    }

    @Autowired
    public void setMatchService(MatchService matchService) {
        this.matchService = matchService;
    }

    @Autowired
    public void setTeamService(TeamService teamService) {
        this.teamService = teamService;
    }

    @Override
    @Transactional
    public List<Group> getAllGroups(Long tournamentId) {
        tournamentService.getTournamentById(tournamentId);
        List<Group> groups = groupRepository.findAllByTournament_Id(tournamentId);
        if (groups.isEmpty()) {
            logger.info("There aren't any created groups");
        }
        return groups;
    }

    @Override
    @Transactional
    public Group getGroupById(Long tournamentId, String groupName) {
        GroupId groupId = new GroupId(groupName);
        Optional<Group> group = groupRepository.findByTournamentIdAndGroupId(tournamentId, groupId);
        if (group.isEmpty()) {
            throw new EntityNotFoundException("group");
        }
        return group.get();
    }

    @Override
    @Transactional
    public Group createGroup(Long tournamentId, GroupDTO groupDTO) {
        Tournament tournament = tournamentService.getTournamentById(tournamentId);
        int numberOfCreatedGroups = getAllGroups(tournamentId).size();
        if (numberOfCreatedGroups + 1 > tournament.getNumberOfGroups()) {
            throw new ExceededLimitException("group");
        }
        String groupName = groupDTO.getGroupName();
        GroupId groupId = new GroupId(groupName);
        if (groupRepository.findById(groupId).isPresent()) {
            throw new DuplicateEntityException("group");
        }
        Group group = new Group();
        group.setGroupId(groupId);
        group.setIsFinished(groupDTO.getIsFinished());
        group.setTournament(tournament);
        return groupRepository.saveAndFlush(group);
    }

    @Override
    @Transactional
    public Group updateGroup(Long tournamentId, String groupName, GroupDTO groupDTO) {
        Group group = getGroupById(tournamentId, groupName);
        group.setIsFinished(groupDTO.getIsFinished());
        if (!groupName.equals(groupDTO.getGroupName())) {
            groupRepository.delete(group);
            List<String> busyNames = groupRepository.findAllByTournament_Id(tournamentId).stream()
                .map(currentGroup -> currentGroup.getGroupId().getGroupName())
                .toList();
            if (busyNames.contains(groupDTO.getGroupName())) {
                throw new DuplicateEntityException("group");
            }
            GroupId groupId = group.getGroupId();
            groupId.setGroupName(groupDTO.getGroupName());
            group.setGroupId(groupId);

        }
        return groupRepository.saveAndFlush(group);
    }

    @Override
    @Transactional
    public void deleteGroupById(Long tournamentId, String groupName) {
        logger.info("Deleting group with tournament id = {} and group name = {}", tournamentId, groupName);
        Group group = getGroupById(tournamentId, groupName);
        groupRepository.delete(group);
    }

    @Override
    @Transactional
    public RankingDTO updateRanking(Long tournamentId, String groupName, String teamName) {
        tournamentService.getTournamentById(tournamentId);
        getGroupById(tournamentId, groupName);
        List<Match> teamMatches = matchService.getAllMatchesInGroupOfTeam(tournamentId,teamName);
        Long points = 0L, goalsScored = 0L, goalsAgainst = 0L, matchesPlayed = 0L, wins = 0L, losses = 0L, draws = 0L;
        for (Match match : teamMatches) {
            if (match.getPlayed()){
                matchesPlayed += 1;
                if (Objects.equals(match.getFirstTeamName(), teamName)) {
                   goalsScored += match.getFirstTeamScoredGoals();
                   goalsAgainst += match.getSecondTeamScoredGoals();
                   if (match.getFirstTeamScoredGoals() > match.getSecondTeamScoredGoals()) wins += 1;
                   else if(match.getFirstTeamScoredGoals() < match.getSecondTeamScoredGoals()) losses +=1;
                   else draws += 1;

                }
                else{
                    goalsAgainst += match.getFirstTeamScoredGoals();
                    goalsScored += match.getSecondTeamScoredGoals();
                    if (match.getFirstTeamScoredGoals() < match.getSecondTeamScoredGoals()) wins += 1;
                    else if(match.getFirstTeamScoredGoals() > match.getSecondTeamScoredGoals()) losses +=1;
                    else draws += 1;

                }
            }
        }
        points = wins * 3 + draws;
        return new RankingDTO(teamName, matchesPlayed, wins, draws, losses, goalsScored, goalsAgainst,goalsScored - goalsAgainst, points);

    }

    @Override
    @Transactional
    public List<RankingDTO> updateTable(Long tournamentId, String groupName){
        List<String> teamsNamesInTheGroup = teamService.getAllTeamsByGroup(tournamentId, groupName).stream()
            .map(Team::getTeamName)
            .toList();
        List<RankingDTO> table = new ArrayList<>();
        for (String teamName : teamsNamesInTheGroup ){
            table.add(updateRanking(tournamentId,groupName,teamName));
        }
        Collections.sort(table);
        return table;
    }
}