package com.example.football.services;

import com.example.football.dto.GoalDTO;
import com.example.football.exceptions.PlayerNotInTheMatchException;
import com.example.football.models.Goal;
import com.example.football.models.Match;
import com.example.football.models.Player;
import com.example.football.models.Team;
import com.example.football.repositories.GoalRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class GoalServiceImpl implements GoalService {

    private GoalRepository goalRepository;

    private TournamentService tournamentService;

    private MatchService matchService;

    private TeamService teamService;

    private PlayerService playerService;

    @Autowired
    public void setGoalRepository(GoalRepository goalRepository) {
        this.goalRepository = goalRepository;
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

    @Autowired
    public void setPlayerService(PlayerService playerService) {
        this.playerService = playerService;
    }


    @Override
    @Transactional
    public List<Goal> getAllGoalsOfAMatch(Long tournamentId, Long matchId) {
        return null;
    }

    @Override
    @Transactional
    public Goal addGoal(Long tournamentId, Long matchId, GoalDTO goalDTO) {
        tournamentService.getTournamentById(tournamentId);
        Match match = matchService.getMatchById(tournamentId, matchId);

        Long playerId = goalDTO.getPlayerId();
        Player player = playerService.getPlayerById(tournamentId, matchId, playerId);
        Team playerTeam = player.getTeam();
        String firstTeamName = match.getFirstTeamName();
        String secondTeamName = match.getSecondTeamName();
        Team firstTeam = teamService.getTeamByName(tournamentId, firstTeamName);
        Team secondTeam = teamService.getTeamByName(tournamentId, secondTeamName);

        if (!playerTeam.getId().equals(firstTeam.getId()) && !playerTeam.getId().equals(secondTeam.getId())) {
            throw new PlayerNotInTheMatchException("");
        }

        Goal goal = new Goal();
        goal.setMatch(match);
        goal.setPlayerId(playerId);
        goal.setMinuteScored(goalDTO.getMinuteScored());

        return goalRepository.saveAndFlush(goal);
    }
}
