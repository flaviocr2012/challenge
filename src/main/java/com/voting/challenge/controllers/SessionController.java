package com.voting.challenge.controllers;

import com.voting.challenge.dtos.SessionRequest;
import com.voting.challenge.enums.VoteStatus;
import com.voting.challenge.models.Session;
import com.voting.challenge.services.SessionService;
import com.voting.challenge.services.WinnerService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.chrono.ChronoLocalDateTime;

@RestController
@RequestMapping("/session")
@CrossOrigin(origins = "*", maxAge = 3600)
public class SessionController {

    private final SessionService sessionService;
    private final WinnerService winnerService;


    @Autowired
    public SessionController(SessionService sessionService, WinnerService winnerService) {
        this.sessionService = sessionService;
        this.winnerService = winnerService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Session> createSession(
            @RequestBody @Valid SessionRequest sessionRequest
            ){
        return sessionService.registerSession(sessionRequest);
    }
    @GetMapping("/agendaId")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public ResponseEntity<VoteStatus>
    getVotingResult(@PathVariable Long agendaId) {
        return winnerService.retrieveVotingResult(agendaId);
    }
}
