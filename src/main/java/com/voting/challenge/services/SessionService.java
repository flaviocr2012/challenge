package com.voting.challenge.services;

import com.voting.challenge.dtos.AgendaRequest;
import com.voting.challenge.dtos.SessionRequest;
import com.voting.challenge.models.Agenda;
import com.voting.challenge.models.Session;
import com.voting.challenge.repositories.SessionRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class SessionService {

    private final SessionRepository sessionRepository;

    private final ModelMapper modelMapper;

    @Autowired
    public SessionService(SessionRepository sessionRepository, ModelMapper modelMapper) {
        this.sessionRepository = sessionRepository;
        this.modelMapper = modelMapper;
    }

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

}
