package com.voting.challenge.services;

import com.voting.challenge.constants.ExceptionConstant;
import com.voting.challenge.dtos.VoteRequest;
import com.voting.challenge.exceptions.SessionException;
import com.voting.challenge.exceptions.VoteException;
import com.voting.challenge.models.Session;
import com.voting.challenge.models.Vote;
import com.voting.challenge.models.VoteId;
import com.voting.challenge.repositories.SessionRepository;
import com.voting.challenge.repositories.VoteRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class VoteService {

    private final VoteRepository voteRepository;
    private final SessionRepository sessionRepository;
    private final ModelMapper modelMapper;

    public ResponseEntity<VoteRequest> registerVoteAndSession(VoteRequest voteRequest) {
        validateVoteRequest(voteRequest);

        var voteId = registerVoteId(voteRequest);
        var vote = registerVote(voteRequest, voteId);
        var session = getSession(voteRequest);
        vote.setSession(session);

        var savedVote = voteRepository.save(vote);
        return ResponseEntity.ok(modelMapper.map(savedVote, VoteRequest.class));
    }

    private void validateVoteRequest(VoteRequest voteRequest) {
        if (voteRepository.existsById(voteRequest)
                ){
            throw new VoteException(ExceptionConstant.VOTE_NOT_FOUND);
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
                .orElseThrow(() -> new SessionException(ExceptionConstant.SESSION_NOT_FOUND));
    }
}

