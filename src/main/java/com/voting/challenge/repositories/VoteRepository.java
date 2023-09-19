package com.voting.challenge.repositories;

import com.voting.challenge.models.Vote;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VoteRepository extends JpaRepository<Vote, Long> {
    boolean existsByAssociatedIdAndSessionId(Long associateId, Long sessionId);
}
