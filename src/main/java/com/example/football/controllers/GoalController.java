package com.example.football.controllers;

import com.example.football.dto.GoalDTO;
import com.example.football.dto.PlayerDTO;
import com.example.football.models.Goal;
import com.example.football.models.Player;
import com.example.football.services.GoalService;
import com.example.football.services.PlayerService;
import java.util.List;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/tournaments/{tournamentId}/goals/{matchId}")
public class GoalController {

    private GoalService goalService;

    @Autowired
    public void setGoalService(GoalService goalService) {
        this.goalService = goalService;
    }

    @PostMapping
    public Goal createGoal(@PathVariable Long tournamentId, @PathVariable Long matchId, @RequestBody @Valid GoalDTO goalDTO) {
        return goalService.addGoal(tournamentId, matchId, goalDTO);
    }

    @GetMapping
    public List<Goal> getAllGoalsOfAMatch(@PathVariable Long tournamentId, @PathVariable Long matchId) {
        return goalService.getAllGoalsOfAMatch(tournamentId, matchId);
    }

    /*@GetMapping(value = "/{goalId}")
    public Goal getGoalById(@PathVariable Long tournamentId, @PathVariable Long matchId, @PathVariable Long goalId) {
        return goalService.getGoalById(tournamentId, matchId, goalId);
    }*/
}
