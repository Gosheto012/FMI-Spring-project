package com.example.football.controllers;

import com.example.football.dto.MatchDTO;
import com.example.football.dto.TeamDTO;
import com.example.football.models.Match;
import com.example.football.models.Team;
import com.example.football.services.MatchService;
import java.util.List;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.parameters.P;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/tournaments/{tournamentId}/matches")
public class MatchController {

    private MatchService matchService;

    @Autowired
    public void setMatchService(MatchService matchService) {
        this.matchService = matchService;
    }

    @GetMapping
    public List<Match> getAllMatches(@PathVariable Long tournamentId) {
        return matchService.getAllMatches(tournamentId);
    }

    @GetMapping(path = "/{matchId}")
    public Match getMatchById(@PathVariable Long tournamentId, @PathVariable Long matchId) {
        return matchService.getMatchById(tournamentId, matchId);
    }

    @GetMapping(path = "/groupMatches/{groupName}")
    public List<Match> getAllMatchesInGroup(@PathVariable Long tournamentId, @PathVariable String groupName) {
        return matchService.getAllMatchesInGroup(tournamentId, groupName);
    }

    @PostMapping
    public Match createMatch(@PathVariable Long tournamentId, @RequestBody @Valid MatchDTO matchDTO) {
        return matchService.createMatch(tournamentId, matchDTO);
    }

    @PutMapping(value = "/{matchId}")
    public Match updateMatch(@PathVariable Long tournamentId, @PathVariable Long matchId, @RequestBody @Valid MatchDTO matchDTO) {
        return matchService.updateMatch(tournamentId, matchId, matchDTO);
    }

    @DeleteMapping(value = "/{matchId}")
    public void deleteMatchById(@PathVariable Long tournamentId, @PathVariable Long matchId) {
        matchService.deleteMatchById(tournamentId, matchId);
    }
}
