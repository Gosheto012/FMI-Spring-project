package com.example.football.repositories;

import com.example.football.models.Player;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface PlayerRepository extends JpaRepository<Player, Long> {
    /*
        @Query(value = "SELECT subscriptionOperation "
        + "FROM SubscriptionOperation subscriptionOperation "
        + "JOIN subscriptionOperation.serviceEnvironment serviceEnvironment "
        + "JOIN serviceEnvironment.serviceInfo serviceInfo "
        + "WHERE serviceInfo.id = ?1 AND serviceEnvironment.label = ?2 "
        + "ORDER BY subscriptionOperation.id DESC")
     */

    @Query(value = "SELECT player FROM Player player "
        + "JOIN player.team team "
        + "JOIN team.tournament tournament "
        + "WHERE tournament.id = ?1 AND team.id = ?2 "
        + "ORDER BY player.id ASC")
    List<Player> findAllByTournamentIdAndTeamId(Long tournamentId, Long teamId);

    @Query(value = "SELECT player FROM Player player "
        + "JOIN player.team team "
        + "JOIN team.tournament tournament "
        + "WHERE tournament.id = ?1 AND team.id = ?2 AND player.firstName = ?3 AND player.secondName = ?4 "
        + "ORDER BY player.id ASC")
    List<Player> findAllByTournamentIdTeamIdAndPlayerName(Long tournamentId, Long teamId, String firstName, String secondName);

    @Query(value = "SELECT player FROM Player player "
        + "JOIN player.team team "
        + "JOIN team.tournament tournament "
        + "WHERE tournament.id = ?1 AND team.id = ?2  AND player.id = ?3")
    Optional<Player> findByPlayerId(Long tournamentId, Long teamId, Long playerId);
}
