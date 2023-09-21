package com.voting.challenge.services;

import com.voting.challenge.constants.ExceptionConstant;
import com.voting.challenge.enums.VoteStatus;
import com.voting.challenge.exceptions.AgendaException;
import com.voting.challenge.models.Vote;
import com.voting.challenge.repositories.SessionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class WinnerService {

    private final SessionRepository sessionRepository;

    public ResponseEntity<VoteStatus> retrieveVotingResult(Long agendaId) {
        var session = sessionRepository.findByAgendaId(agendaId)
                .orElseThrow(() -> new AgendaException(ExceptionConstant.AGENDA_NOT_FOUND));

        var winner = determineWinningVoteStatus(session.getVotes());
        return ResponseEntity.ok(winner);
    }

    private VoteStatus determineWinningVoteStatus(List<Vote> votes) {
        Map<VoteStatus, Long> totalVotes = countVotes(votes);
        return totalVotes.get(VoteStatus.YES).compareTo(totalVotes.get(VoteStatus.NO)) >= 1 ? VoteStatus.YES : VoteStatus.NO;
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
