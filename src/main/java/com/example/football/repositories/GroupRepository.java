package com.example.football.repositories;

import com.example.football.models.Group;
import com.example.football.models.ids.GroupId;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface GroupRepository extends JpaRepository<Group, GroupId> {
    List<Group> findAllByTournament_Id(Long tournamentId);
    Optional<Group> findByTournamentIdAndGroupId(Long tournamentId, GroupId groupId);
}
