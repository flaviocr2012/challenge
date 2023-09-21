package com.voting.challenge.services;

import com.voting.challenge.constants.ExceptionConstant;
import com.voting.challenge.dtos.SessionRequest;
import com.voting.challenge.exceptions.SessionException;
import com.voting.challenge.models.Session;
import com.voting.challenge.repositories.SessionRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class SessionService {

    private final SessionRepository sessionRepository;
    private final ModelMapper modelMapper;


    public ResponseEntity<Session> registerSession(SessionRequest sessionRequest
    ) {
        isInsideLimit(sessionRequest);
        return Optional.of(sessionRequest)
                .map(this::mapSession)
                .map(sessionRepository::save)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.badRequest().build());
    }

    private Session mapSession(SessionRequest sessionRequest) {
        return modelMapper.map(sessionRequest, Session.class);
    }

    private void isInsideLimit(SessionRequest sessionRequest) {
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime start = sessionRequest.getStart();
        LocalDateTime end = sessionRequest.getEnd();

        if (now.isBefore(start) || now.isAfter(end)) {
            throw new SessionException(ExceptionConstant.SESSION_EXCEED_LIMIT);
        }
    }
}
