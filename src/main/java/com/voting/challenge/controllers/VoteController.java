package com.voting.challenge.controllers;

import com.voting.challenge.dtos.VoteRequest;
import com.voting.challenge.models.Vote;
import com.voting.challenge.services.VoteService;
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
    public ResponseEntity<Vote> createVote(
            @RequestBody VoteRequest voteRequest) {
        return voteService.registerVoteAndSession(voteRequest);
    }
}
