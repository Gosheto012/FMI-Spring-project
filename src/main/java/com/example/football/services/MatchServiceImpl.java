package com.example.football.services;

import com.example.football.dto.MatchDTO;
import com.example.football.exceptions.EntityNotFoundException;
import com.example.football.exceptions.TeamInMatchNotChangeNamesException;
import com.example.football.exceptions.TeamsNotInTheSameGroupException;
import com.example.football.models.Match;
import com.example.football.models.Team;
import com.example.football.models.Tournament;
import com.example.football.repositories.MatchRepository;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class MatchServiceImpl implements  MatchService {

    private static final Logger logger = LoggerFactory.getLogger(MatchServiceImpl.class);

    private MatchRepository matchRepository;

    private TournamentService tournamentService;

    private TeamService teamService;

    private GroupService groupService;

    @Autowired
    public void setMatchRepository(MatchRepository matchRepository) {
        this.matchRepository = matchRepository;
    }

    @Autowired
    public void setTournamentService(TournamentService tournamentService) {
        this.tournamentService = tournamentService;
    }

    @Autowired
    public void setTeamService(TeamService teamService) {
        this.teamService = teamService;
    }

    @Autowired
    public void setGroupService(GroupService groupService) {
        this.groupService = groupService;
    }

    @Override
    @Transactional
    public List<Match> getAllMatches(Long tournamentId) {
        tournamentService.getTournamentById(tournamentId);
        List<Match> matches = matchRepository.findAllByTournamentId(tournamentId);
        if (matches.isEmpty()) {
            logger.info("There aren't any matches");
        }
        return matches;
    }

    @Override
    @Transactional
    public Match getMatchById(Long tournamentId, Long matchId) {
        tournamentService.getTournamentById(tournamentId);
        Optional<Match> match = matchRepository.findByTournamentIdAndId(tournamentId, matchId);
        if (match.isEmpty()) {
            throw new EntityNotFoundException("match");
        }
        return match.get();
    }

    @Override
    @Transactional
    public List<Match> getAllMatchesInGroup(Long tournamentId, String groupName) {
        tournamentService.getTournamentById(tournamentId);
        groupService.getGroupById(tournamentId, groupName);
        List<String> teamsNamesInTheGroup = teamService.getAllTeamsByGroup(tournamentId, groupName).stream()
            .map(Team::getTeamName)
            .toList();
        List<Match> allMatches = getAllMatches(tournamentId);
        return allMatches.stream()
            .filter((match) -> teamsNamesInTheGroup.contains(match.getFirstTeamName())).filter((match)->match.getGroupStageMatch() )
            .toList();
    }

    /*
        "firstTeamName" : "Lionel",
    "secondTeamName" : "Messi",
    "firstTeamScoredGoals" : "",
    "secondTeamScoredGoals" : "",
    "isPlayed" : false,
    "isGroupStageMatch" :
     */
    /*
    {
    "firstTeamName" : "Barcelona",
    "secondTeamName" : "Atletico Madrid",
    "firstTeamScoredGoals" : 5,
    "secondTeamScoredGoals" : 0,
    "isPlayed" : false,
    "isGroupStageMatch" : true
}
     */

    @Override
    @Transactional
    public Match createMatch(Long tournamentId, MatchDTO matchDTO) {
        Tournament tournament = tournamentService.getTournamentById(tournamentId);

        String firstTeamName = matchDTO.getFirstTeamName();
        String secondTeamName = matchDTO.getSecondTeamName();
        Boolean isPlayed = matchDTO.isPlayed();
        Boolean isGroupStageMatch = matchDTO.isGroupStageMatch();

        if (!teamService.existsByTournamentAndName(tournamentId, firstTeamName)
            || !teamService.existsByTournamentAndName(tournamentId, secondTeamName)) {
            throw new EntityNotFoundException("team");
        }

        if (isGroupStageMatch) {
           if (!teamService.areInTheSameGroup(tournamentId, firstTeamName, secondTeamName)) {
               throw new TeamsNotInTheSameGroupException(firstTeamName, secondTeamName);
           }
        }

        /*
        PROVERKA DALI SA V EDNA FAZA NA NADPREVARATA
         */

        Match match = new Match();
        match.setFirstTeamName(firstTeamName);
        match.setSecondTeamName(secondTeamName);
        match.setPlayed(isPlayed);
        match.setGroupStageMatch(isGroupStageMatch);
        match.setTournament(tournament);

        if (!isPlayed) {
            match.setFirstTeamScoredGoals(0L);
            match.setSecondTeamScoredGoals(0L);
        }
        else {
            match.setFirstTeamScoredGoals(matchDTO.getFirstTeamScoredGoals());
            match.setSecondTeamScoredGoals(matchDTO.getSecondTeamScoredGoals());
        }

        return matchRepository.saveAndFlush(match);
    }

    @Override
    @Transactional
    public Match updateMatch(Long tournamentId, Long matchId, MatchDTO matchDTO) {
        tournamentService.getTournamentById(tournamentId);
        Optional<Match> possibleMatch = matchRepository.findByTournamentIdAndId(tournamentId, matchId);
        if (possibleMatch.isEmpty()) {
            throw new EntityNotFoundException("match");
        }
        String firstTeamName = matchDTO.getFirstTeamName();
        String secondTeamName = matchDTO.getSecondTeamName();
        Match match = possibleMatch.get();
        Boolean isPlayed = matchDTO.isPlayed();
        Boolean isGroupStageMatch = matchDTO.isGroupStageMatch();
        if (!match.getFirstTeamName().equals(firstTeamName) || !match.getSecondTeamName().equals(secondTeamName)) {
            throw new TeamInMatchNotChangeNamesException("");
        }
        match.setPlayed(isPlayed);
        match.setGroupStageMatch(isGroupStageMatch);

        if (!isPlayed) {
            match.setFirstTeamScoredGoals(0L);
            match.setSecondTeamScoredGoals(0L);
        }

        else {
            match.setFirstTeamScoredGoals(matchDTO.getFirstTeamScoredGoals());
            match.setSecondTeamScoredGoals(matchDTO.getSecondTeamScoredGoals());
        }

        return matchRepository.saveAndFlush(match);
    }

    @Override
    @Transactional
    public void deleteMatchById(Long tournamentId, Long matchId) {
        logger.info("Deleting match with tournament id = {} and match id = {}", tournamentId, matchId);
        Match match = getMatchById(tournamentId, matchId);
        matchRepository.delete(match);
    }

    @Override
    @Transactional
    public List<Match> getAllMatchesInGroupOfTeam(Long tournamentId, String teamName){
        tournamentService.getTournamentById(tournamentId);
        List<Match> allMatches = getAllMatches(tournamentId);
        return allMatches.stream()
            .filter((match) ->(Objects.equals(match.getFirstTeamName(), teamName) || Objects.equals(match.getSecondTeamName(), teamName)))
                .filter((match)->match.getGroupStageMatch())
            .toList();

    }
}
