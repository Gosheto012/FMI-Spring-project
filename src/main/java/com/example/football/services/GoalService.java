package com.example.football.services;

import com.example.football.dto.GoalDTO;
import com.example.football.models.Goal;
import java.util.List;
import javax.validation.Valid;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

public interface GoalService {

    Goal addGoal(Long tournamentId, Long matchId, GoalDTO goalDTO);

    List<Goal> getAllGoalsOfAMatch(Long tournamentId, Long matchId);
}
