package com.example.football.controllers;

import com.example.football.dto.TournamentDTO;
import com.example.football.models.Tournament;
import com.example.football.services.TournamentService;
import java.util.ArrayList;
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
@RequestMapping("/api/tournaments")
public class TournamentController {

    private TournamentService tournamentService;

    @Autowired
    public void setTournamentService(TournamentService tournamentService) {
        this.tournamentService = tournamentService;
    }

    @GetMapping
    public List<Tournament> getAllTournaments() {
        return tournamentService.getAllTournaments();
    }

    @GetMapping(value = "/{tournamentId}")
    public Tournament getTournamentById(@PathVariable Long tournamentId) {
        return tournamentService.getTournamentById(tournamentId);
    }

    @PostMapping
    public Tournament createTournament(@RequestBody @Valid TournamentDTO tournamentDTO) {
        return tournamentService.createTournament(tournamentDTO);
    }

    @PutMapping(value = "/{tournamentId})")
    public Tournament updateTournament(@PathVariable Long tournamentId, @RequestBody @Valid TournamentDTO tournamentDTO) {
        return tournamentService.updateTournament(tournamentId, tournamentDTO);
    }

    @DeleteMapping(value = "/{tournamentId})")
    public void deleteTournamentById(@PathVariable Long tournamentId) {
        tournamentService.deleteTournamentById(tournamentId);
    }
}
