package com.voting.challenge.services;

import com.voting.challenge.dtos.SessionRequest;
import com.voting.challenge.enums.VoteStatus;
import com.voting.challenge.models.Session;
import com.voting.challenge.models.Vote;
import com.voting.challenge.repositories.SessionRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SessionService {

    private final SessionRepository sessionRepository;

    private final ModelMapper modelMapper;
    private final List<Vote> votes = new ArrayList<>();

    public ResponseEntity<Session> createSession(SessionRequest sessionRequest) {
        return Optional.ofNullable(sessionRequest)
                .map(this::mapSession)
                .map(sessionRepository::save)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.badRequest().build());
    }

    private Session mapSession(SessionRequest sessionRequest) {
        return modelMapper.map(sessionRequest, Session.class);
    }

    private Map<VoteStatus, Long> countVotes() {
        var votingBox = new HashMap<VoteStatus, Long>();
        validateVotes(votes, votingBox);
        return votingBox;
    }


    private void validateVotes(List<Vote> votes, Map<VoteStatus, Long> votingBox) {
        votes.forEach(vote -> {
            switch (vote.getVoteStatus()) {
                case YES:
                    var yesVotes = votingBox.getOrDefault(VoteStatus.YES, 0L);
                    votingBox.put(VoteStatus.YES, yesVotes + 1L);
                    break;
                case NO:
                    var noVotes = votingBox.getOrDefault(VoteStatus.NO, 0L);
                    votingBox.put(VoteStatus.NO, noVotes + 1L);
                    break;
            }
        });
    }


    public ResponseEntity<VoteStatus> retrieveVotingResult(Long agendaId) {
        sessionRepository.findByAgendaId(agendaId).orElseThrow(()
                -> new EntityNotFoundException(" Could not find agenda"));

        return ResponseEntity.ok(retrieveWinner());

    }

    public VoteStatus retrieveWinner() {
        Map<VoteStatus, Long> totalVotes = countVotes();
        return totalVotes.get(VoteStatus.YES).compareTo(
                totalVotes.get(VoteStatus.NO)) >= 1 ? VoteStatus.YES : VoteStatus.NO;
    }

}
