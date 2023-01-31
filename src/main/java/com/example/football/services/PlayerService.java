package com.example.football.services;

import com.example.football.dto.PlayerDTO;
import com.example.football.models.Player;
import java.util.List;

public interface PlayerService {

    Player createPlayer(Long tournamentId, Long teamId, PlayerDTO playerDTO);

    List<Player> getAllPlayersOfATeam(Long tournamentId, Long teamId);

    List<Player> getAllPlayersByTournamentIdAndName(Long tournamentId, Long teamId, String firstName, String secondName);

    Player getPlayerById(Long tournamentId, Long teamId, Long playerId);

    Player updatePlayer(Long tournamentId, Long teamId, Long playerId, PlayerDTO playerDTO);

    void deletePlayerById(Long tournamentId, Long teamId, Long playerId);
}
