package com.example.football.repositories;

import com.example.football.models.Team;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TeamRepository extends JpaRepository<Team, Long> {
    List<Team> findAllByTournament_Id(Long tournamentId);
    List<Team> findAllByTournament_IdAndGroupName(Long tournamentId, String groupName);
    Optional<Team> findByTournamentIdAndTeamName(Long tournamentId, String teamName);
    Optional<Team> findByTournamentIdAndId(Long tournamentId, Long teamId);
}
