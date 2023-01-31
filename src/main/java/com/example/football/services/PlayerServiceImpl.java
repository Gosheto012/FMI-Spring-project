package com.example.football.services;

import com.example.football.dto.PlayerDTO;
import com.example.football.exceptions.EntityNotFoundException;
import com.example.football.models.Player;
import com.example.football.models.Team;
import com.example.football.repositories.PlayerRepository;
import java.util.List;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class PlayerServiceImpl implements PlayerService {

    private static final Logger logger = LoggerFactory.getLogger(PlayerServiceImpl.class);

    private TournamentService tournamentService;

    private TeamService teamService;

    private PlayerRepository playerRepository;

    @Autowired
    public void setPlayerRepository(PlayerRepository playerRepository) {
        this.playerRepository = playerRepository;
    }

    @Autowired
    public void setTournamentService(TournamentService tournamentService) {
        this.tournamentService = tournamentService;
    }

    @Autowired
    public void setTeamService(TeamService teamService) {
        this.teamService = teamService;
    }

    @Override
    @Transactional
    public List<Player> getAllPlayersOfATeam(Long tournamentId, Long teamId) {
        tournamentService.getTournamentById(tournamentId);
        teamService.getTeamById(tournamentId, teamId);
        List<Player> players = playerRepository.findAllByTournamentIdAndTeamId(tournamentId, teamId);
        if (players.isEmpty()) {
            logger.info("There aren't any players in the given team");
        }
        return players;
    }


    @Override
    @Transactional
    public List<Player> getAllPlayersByTournamentIdAndName(Long tournamentId, Long teamId, String firstName, String secondName) {
        tournamentService.getTournamentById(tournamentId);
        teamService.getTeamById(tournamentId, teamId);
        List<Player> players = playerRepository.findAllByTournamentIdTeamIdAndPlayerName(tournamentId, teamId, firstName, secondName);
        if (players.isEmpty()) {
            logger.info("There aren't any players with the same name from the given group");
        }
        return players;
    }

    @Override
    @Transactional
    public Player getPlayerById(Long tournamentId, Long teamId, Long playerId) {
        tournamentService.getTournamentById(tournamentId);
        teamService.getTeamById(tournamentId, teamId);
        Optional<Player> player = playerRepository.findByPlayerId(tournamentId, teamId, playerId);
        if (player.isEmpty()) {
            throw new EntityNotFoundException("player");
        }
        return player.get();
    }

    @Override
    @Transactional
    public Player createPlayer(Long tournamentId, Long teamId, PlayerDTO playerDTO) {
        tournamentService.getTournamentById(tournamentId);
        Team playerTeam = teamService.getTeamById(tournamentId, teamId);
        Player player = new Player();
        player.setFirstName(playerDTO.getFirstName());
        player.setSecondName(playerDTO.getSecondName());
        player.setTeam(playerTeam);
        Double playerRating = playerDTO.getRating() == null ? 6.00 : playerDTO.getRating();
        player.setPlayerRating(playerRating);
        return playerRepository.saveAndFlush(player);
    }

    @Override
    @Transactional
    public Player updatePlayer(Long tournamentId, Long teamId, Long playerId, PlayerDTO playerDTO) {
        tournamentService.getTournamentById(tournamentId);
        teamService.getTeamById(tournamentId, teamId);
        Optional<Player> player = playerRepository.findByPlayerId(tournamentId, teamId, playerId);
        if (player.isEmpty()) {
            throw new EntityNotFoundException("player");
        }
        Player playerToSave = player.get();
        playerToSave.setFirstName(playerDTO.getFirstName());
        playerToSave.setSecondName(playerDTO.getSecondName());
        playerToSave.setPlayerRating(playerDTO.getRating());
        return playerRepository.saveAndFlush(playerToSave);
    }

    @Override
    @Transactional
    public void deletePlayerById(Long tournamentId, Long teamId, Long playerId) {
        logger.info("Deleting player with tournament id = {}, team id = {} and id = {}", tournamentId, teamId, playerId);
        Player player = getPlayerById(tournamentId, teamId, playerId);
        playerRepository.delete(player);
    }
}