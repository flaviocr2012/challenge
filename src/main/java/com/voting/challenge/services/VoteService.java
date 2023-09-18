package com.voting.challenge.services;

import com.voting.challenge.dtos.VoteRequest;
import com.voting.challenge.models.Session;
import com.voting.challenge.models.Vote;
import com.voting.challenge.models.VoteId;
import com.voting.challenge.repositories.SessionRepository;
import com.voting.challenge.repositories.VoteRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class VoteService {

    private final VoteRepository voteRepository;
    private final SessionRepository sessionRepository;

    public ResponseEntity<Vote> registerVoteAndSession(VoteRequest voteRequest) {
        var voteId = createVoteId(voteRequest);
        var vote = createVote(voteRequest, voteId);
        var session = getSession(voteRequest);
        vote.setSession(session);

        var savedVote = voteRepository.save(vote);
        return ResponseEntity.ok(savedVote);
    }

    private VoteId createVoteId(VoteRequest voteRequest) {
        return VoteId.builder()
                .sessionId(voteRequest.getSessionId())
                .associateId(voteRequest.getAssociateId())
                .build();
    }

    private Vote createVote(VoteRequest voteRequest, VoteId voteId) {
        return Vote.builder()
                .id(voteId)
                .voteStatus(voteRequest.getVoteStatus())
                .build();
    }

    private Session getSession(VoteRequest voteRequest) {
        return sessionRepository.findById(voteRequest.getSessionId())
                .orElseThrow(() -> new EntityNotFoundException("Could not find session"));
    }
}

