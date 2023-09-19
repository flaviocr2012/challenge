package com.voting.challenge.services;

import com.voting.challenge.dtos.SessionRequest;
import com.voting.challenge.enums.VoteStatus;
import com.voting.challenge.models.Session;
import com.voting.challenge.models.Vote;
import com.voting.challenge.repositories.SessionRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@RequiredArgsConstructor
public class SessionService {

    private final SessionRepository sessionRepository;
    private final ModelMapper modelMapper;

    public ResponseEntity<Session> createSession(SessionRequest sessionRequest) {
        return Optional.ofNullable(sessionRequest)
                .map(this::mapSession)
                .map(sessionRepository::save)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.badRequest().build());
    }

    public ResponseEntity<VoteStatus> retrieveVotingResult(Long agendaId) {
        var session = sessionRepository.findByAgendaId(agendaId)
                .orElseThrow(() -> new EntityNotFoundException("Could not find agenda"));

        var winner = determineWinningVoteStatus(session.getVotes());
        return ResponseEntity.ok(winner);
    }

    public VoteStatus determineWinningVoteStatus(List<Vote> votes) {
        Map<VoteStatus, Long> totalVotes = countVotes(votes);
        return totalVotes.get(VoteStatus.YES).compareTo(totalVotes.get(VoteStatus.NO)) >= 1 ? VoteStatus.YES : VoteStatus.NO;
    }

    private Session mapSession(SessionRequest sessionRequest) {
        return modelMapper.map(sessionRequest, Session.class);
    }

    private Map<VoteStatus, Long> countVotes(List<Vote> votes) {
        Map<VoteStatus, Long> votingBox = new HashMap<>();
        votes.forEach(vote -> {
            switch (vote.getVoteStatus()) {
                case YES:
                    final long yesVotesCount = votingBox.getOrDefault(VoteStatus.YES, 0L);
                    votingBox.put(VoteStatus.YES, yesVotesCount + 1L);
                    break;
                case NO:
                    final long noVotesCount = votingBox.getOrDefault(VoteStatus.NO, 0L);
                    votingBox.put(VoteStatus.NO, noVotesCount + 1L);
                    break;
            }
        });
        return votingBox;
    }
}
