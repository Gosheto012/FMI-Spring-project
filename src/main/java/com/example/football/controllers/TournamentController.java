package com.example.football.controllers;

import com.example.football.dto.TournamentDTO;
import com.example.football.models.Tournament;
import com.example.football.services.TournamentService;
import java.util.List;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/tournaments")
@Validated
public class TournamentController {

    private TournamentService tournamentService;

    @Autowired
    public void setTournamentService(TournamentService tournamentService) {
        this.tournamentService = tournamentService;
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping
    public List<Tournament> getAllTournaments() {
        return tournamentService.getAllTournaments();
    }

    @GetMapping(value = "/{tournamentId}")
    public Tournament getTournamentById(@PathVariable Long tournamentId) {
        return tournamentService.getTournamentById(tournamentId);
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping(path = "/tournamentName")
    public Tournament getTournamentByName(String tournamentName) {
        return tournamentService.getTournamentByName(tournamentName);
    }

    @PostMapping
    public Tournament createTournament(@RequestBody @Validated @Valid TournamentDTO tournamentDTO) {
        return tournamentService.createTournament(tournamentDTO);
    }

    @RequestMapping(value = "/{tournamentId}",
        produces = "application/json",
        method = RequestMethod.PUT)
    public Tournament updateTournament(@PathVariable Long tournamentId, @Valid @RequestBody TournamentDTO tournamentDTO) {
        return tournamentService.updateTournament(tournamentId, tournamentDTO);
    }

    @RequestMapping(value = "/{tournamentId}",
        produces = "application/json",
        method = RequestMethod.DELETE)
    public void deleteTournamentById(@PathVariable Long tournamentId) {
        tournamentService.deleteTournamentById(tournamentId);
    }
}
