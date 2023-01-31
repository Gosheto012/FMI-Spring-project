package com.example.football.controllers;

import com.example.football.dto.PlayerDTO;
import com.example.football.dto.TeamDTO;
import com.example.football.models.Player;
import com.example.football.models.Team;
import com.example.football.services.PlayerService;
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
@RequestMapping("/api/tournaments/{tournamentId}/players/{teamId}")
public class PlayerController {

    private PlayerService playerService;

    @Autowired
    public void setPlayerService(PlayerService playerService) {
        this.playerService = playerService;
    }

    @GetMapping
    public List<Player> getAllPlayersOfATeam(@PathVariable Long tournamentId, @PathVariable Long teamId) {
        return playerService.getAllPlayersOfATeam(tournamentId, teamId);
    }

    @GetMapping(value = "/{playerId}")
    public Player getPlayerById(@PathVariable Long tournamentId, @PathVariable Long teamId, @PathVariable Long playerId) {
        return playerService.getPlayerById(tournamentId, teamId, playerId);
    }

    @PostMapping
    public Player createPlayer(@PathVariable Long tournamentId, @PathVariable Long teamId, @RequestBody @Valid PlayerDTO playerDTO) {
        return playerService.createPlayer(tournamentId, teamId, playerDTO);
    }

    @PutMapping(value = "/{playerId}")
    public Player updatePlayer(@PathVariable Long tournamentId,
                               @PathVariable Long teamId,
                               @PathVariable Long playerId,
                               @RequestBody @Valid PlayerDTO playerDTO) {
        return playerService.updatePlayer(tournamentId, teamId, playerId, playerDTO);
    }

    @DeleteMapping(value = "/{playerId}")
    public void deletePlayerById(@PathVariable Long tournamentId, @PathVariable Long teamId, @PathVariable Long playerId) {
        playerService.deletePlayerById(tournamentId, teamId, playerId);
    }
}
