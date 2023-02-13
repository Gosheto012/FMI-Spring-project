package com.example.football.errorHandlers;

import com.example.football.exceptions.DuplicateEntityException;
import com.example.football.exceptions.EntityNotFoundException;
import com.example.football.exceptions.ExceededLimitException;
import com.example.football.exceptions.PlayerNotInTheMatchException;
import com.example.football.exceptions.TeamInMatchNotChangeNamesException;
import com.example.football.exceptions.TeamsNotInTheSameGroupException;
import javax.persistence.NonUniqueResultException;
import javax.validation.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class ExeptionsErrorHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler({DuplicateEntityException.class, NonUniqueResultException.class})
    public ResponseEntity<Object> handleDuplicateEntityException(DuplicateEntityException ex) {
        return new ResponseEntity<>(String.format("Entity %s with such id or name already exists", ex.getMessage()), HttpStatus.CONFLICT);
    }

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<Object> handleEntityNotFoundException(EntityNotFoundException ex) {
        return new ResponseEntity<>(String.format("Entity %s with such id or name doesn't exist", ex.getMessage()), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(ExceededLimitException.class)
    public ResponseEntity<Object> handleExceededLimitException(ExceededLimitException ex) {
        return new ResponseEntity<>(String.format("Entity %s cannot be created because the limit "
            + "has been reached", ex.getMessage()), HttpStatus.FORBIDDEN);
    }

    @ExceptionHandler(TeamsNotInTheSameGroupException.class)
    public ResponseEntity<Object> handleTeamsNotInTheSameGroupException(String firstTeamName, String secondTeamName) {
        return new ResponseEntity<>(String.format("Team %s and team %s are not in the same group", firstTeamName, secondTeamName),
            HttpStatus.CONFLICT);
    }

    @ExceptionHandler(TeamInMatchNotChangeNamesException.class)
    public ResponseEntity<Object> handleTeamInMatchNotChangeNamesException(TeamInMatchNotChangeNamesException ex) {
        return new ResponseEntity<>("The teams must have the same name from the match you want to change",
            HttpStatus.FORBIDDEN);
    }

    @ExceptionHandler(PlayerNotInTheMatchException.class)
    public ResponseEntity<Object> handlePlayerNotInTheMatchException(PlayerNotInTheMatchException ex) {
        return new ResponseEntity<>("The given player doesn't play in this match", HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<Object> handleConstraintViolationException(MethodArgumentNotValidException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
    }
}