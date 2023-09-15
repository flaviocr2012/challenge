package com.voting.challenge.services;

import com.voting.challenge.dtos.AgendaRequest;
import com.voting.challenge.models.Agenda;
import com.voting.challenge.repositories.AgendaRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AgendaService {

    private final AgendaRepository agendaRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public AgendaService(AgendaRepository agendaRepository, ModelMapper modelMapper) {
        this.agendaRepository = agendaRepository;
        this.modelMapper = modelMapper;
    }

    public ResponseEntity<Agenda> createAgenda(AgendaRequest agendaRequest) {
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
