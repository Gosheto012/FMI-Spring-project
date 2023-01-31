package com.example.football.services;

import com.example.football.dto.TournamentDTO;
import com.example.football.models.Tournament;
import java.util.List;

public interface TournamentService {

    Tournament createTournament(TournamentDTO tournamentDTO);

    Tournament updateTournament(Long tournamentId,TournamentDTO tournamentDTO);

    Tournament getTournamentById(Long tournamentId);

    void deleteTournamentById(Long tournamentId);

    List<Tournament> getAllTournaments();
}
