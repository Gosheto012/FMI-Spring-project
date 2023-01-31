package com.example.football.repositories;

import com.example.football.models.Match;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MatchRepository extends JpaRepository<Match, Long> {
    List<Match> findAllByTournamentId(Long tournamentId);
    Optional<Match> findByTournamentIdAndId(Long tournamentId, Long matchId);
}
