package com.voting.challenge.services;

import com.voting.challenge.constants.ExceptionConstant;
import com.voting.challenge.dtos.SessionRequest;
import com.voting.challenge.enums.VoteStatus;
import com.voting.challenge.exceptions.AgendaException;
import com.voting.challenge.models.Session;
import com.voting.challenge.models.Vote;
import com.voting.challenge.repositories.SessionRepository;
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


    private Session mapSession(SessionRequest sessionRequest) {
        return modelMapper.map(sessionRequest, Session.class);
    }

}
