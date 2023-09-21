package com.voting.challenge.controllers;

import com.voting.challenge.dtos.VoteRequest;
import com.voting.challenge.models.Vote;
import com.voting.challenge.services.VoteService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/vote")
@RequiredArgsConstructor
public class VoteController {

    private final VoteService voteService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<VoteRequest> createVote(
            @RequestBody @Valid VoteRequest voteRequest) {
        return voteService.registerVoteAndSession(voteRequest);
    }
}
