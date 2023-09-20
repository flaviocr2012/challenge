package com.voting.challenge.services;

import com.voting.challenge.dtos.AgendaRequest;
import com.voting.challenge.exceptions.AgendaException;
import com.voting.challenge.models.Agenda;
import com.voting.challenge.repositories.AgendaRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.chrono.ChronoLocalDateTime;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AgendaService {

    private final AgendaRepository agendaRepository;
    private final ModelMapper modelMapper;



    public ResponseEntity<Agenda> registerAgenda(AgendaRequest agendaRequest) {
        return Optional.ofNullable(agendaRequest)
                .map(this::mapAgenda)
                .map(agendaRepository::save)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.badRequest().build());
    }


    private Agenda mapAgenda(AgendaRequest agendaRequest) {
        return modelMapper.map(agendaRequest, Agenda.class);
    }


}
