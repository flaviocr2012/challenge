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
        validateVoteRequest(voteRequest);

        var voteId = registerVoteId(voteRequest);
        var vote = registerVote(voteRequest, voteId);
        var session = getSession(voteRequest);
        vote.setSession(session);

        var savedVote = voteRepository.save(vote);
        return ResponseEntity.ok(savedVote);
    }

    private void validateVoteRequest(VoteRequest voteRequest) {
        if (voteRepository.existsByAssociatedIdAndSessionId(
                voteRequest.getAssociateId(), voteRequest.getSessionId())) {
            throw new IllegalStateException("Vote has already been registered");
        }
    }

    private VoteId registerVoteId(VoteRequest voteRequest) {
        return VoteId.builder()
                .sessionId(voteRequest.getSessionId())
                .associateId(voteRequest.getAssociateId())
                .build();
    }

    private Vote registerVote(VoteRequest voteRequest, VoteId voteId) {
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

