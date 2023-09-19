package com.voting.challenge.controllers;

import com.voting.challenge.dtos.SessionRequest;
import com.voting.challenge.enums.VoteStatus;
import com.voting.challenge.models.Session;
import com.voting.challenge.services.SessionService;
import jakarta.validation.Valid;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/session")
public class SessionController {

    private final SessionService sessionService;

    @Autowired
    public SessionController(SessionService sessionService) {
        this.sessionService = sessionService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Session> createSession(
            @RequestBody @Valid SessionRequest sessionRequest
            ){
        return sessionService.createSession(sessionRequest);
    }

    @GetMapping("/agendaId")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public ResponseEntity<VoteStatus>
    getVotingResult(@PathVariable Long agendaId) {
        return
        sessionService.retrieveVotingResult(agendaId);
    }
}
