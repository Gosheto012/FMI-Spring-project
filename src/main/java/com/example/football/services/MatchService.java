package com.example.football.services;

import com.example.football.dto.MatchDTO;
import com.example.football.models.Match;
import java.util.List;
import javax.validation.Valid;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

public interface MatchService {

    List<Match> getAllMatches(Long tournamentId);

    Match getMatchById(Long tournamentId, Long matchId);

    List<Match> getAllMatchesInGroup(Long tournamentId, String groupName);
    Match createMatch(Long tournamentId, MatchDTO matchDTO);

    Match updateMatch(Long tournamentId, Long matchId, MatchDTO matchDTO);

    void deleteMatchById(Long tournamentId, Long matchId);
}
