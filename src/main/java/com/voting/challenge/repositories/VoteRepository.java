package com.voting.challenge.repositories;

import com.voting.challenge.dtos.VoteRequest;
import com.voting.challenge.models.Vote;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VoteRepository extends JpaRepository<Vote, VoteRequest> {
    @Override
    boolean existsById(VoteRequest voteRequest);
}
