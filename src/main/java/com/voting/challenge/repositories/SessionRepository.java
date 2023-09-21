package com.voting.challenge.repositories;

import com.voting.challenge.dtos.SessionRequest;
import com.voting.challenge.models.Session;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SessionRepository extends JpaRepository<Session, Long> {

    Optional<Session> findByAgendaId(Long Id);
}
