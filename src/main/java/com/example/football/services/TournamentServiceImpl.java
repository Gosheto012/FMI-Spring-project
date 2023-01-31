package com.example.football.services;

import com.example.football.dto.TournamentDTO;
import com.example.football.exceptions.DuplicateEntityException;
import com.example.football.exceptions.EntityNotFoundException;
import com.example.football.models.Tournament;
import com.example.football.repositories.TournamentRepository;
import java.util.List;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class TournamentServiceImpl implements TournamentService {

    private static final Logger logger = LoggerFactory.getLogger(TournamentServiceImpl.class);

    private TournamentRepository tournamentRepository;

    @Autowired
    public void setTournamentRepository(TournamentRepository tournamentRepository) {
        this.tournamentRepository = tournamentRepository;
    }

    @Override
    @Transactional
    public List<Tournament> getAllTournaments() {
        List<Tournament> tournaments = tournamentRepository.findAll();
        if (tournaments.isEmpty()) {
            logger.info("There aren't any created tournaments");
        }
        return tournaments;
    }

    @Override
    @Transactional
    public Tournament getTournamentById(Long tournamentId) {
        Optional<Tournament> tournament = tournamentRepository.findById(tournamentId);
        if (tournament.isEmpty()) {
            throw new EntityNotFoundException("tournament");
        }
        return tournament.get();
    }

    @Override
    @Transactional
    public Tournament createTournament(TournamentDTO tournamentDTO) {
        String tournamentName = tournamentDTO.getTournamentName();
        if (tournamentRepository.findTournamentByTournamentName(tournamentName).isPresent()) {
            throw new DuplicateEntityException("tournament");
        }
        Tournament tournament = new Tournament();
        tournament.setTournamentName(tournamentDTO.getTournamentName());
        tournament.setNumberOfGroups(tournamentDTO.getNumberOfGroups());
        return tournamentRepository.saveAndFlush(tournament);
    }

    @Override
    @Transactional
    public Tournament updateTournament(Long tournamentId,TournamentDTO tournamentDTO) {
        Optional<Tournament> tournament = tournamentRepository.findById(tournamentId);
        if (tournament.isEmpty()) {
            throw new EntityNotFoundException("tournament");
        }
        Tournament tournamentToStore = tournament.get();
        tournamentToStore.setTournamentName(tournamentDTO.getTournamentName());
        tournamentToStore.setNumberOfGroups(tournamentDTO.getNumberOfGroups());
        tournamentToStore.setDescription(tournamentDTO.getDescription());
        return tournamentRepository.saveAndFlush(tournamentToStore);
    }

    @Override
    @Transactional
    public void deleteTournamentById(Long tournamentId) {
        logger.info("Deleting tournament with id = {}", tournamentId);
        tournamentRepository.deleteById(tournamentId);
    }
}
