package com.voting.challenge.controllers;


import com.voting.challenge.dtos.AgendaRequest;
import com.voting.challenge.models.Agenda;
import com.voting.challenge.services.AgendaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/agenda")
public class AgendaController {

    private final AgendaService agendaService;

    @Autowired
    public AgendaController(AgendaService agendaService) {
        this.agendaService = agendaService;
    }

    @PostMapping(produces = "application/json")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Agenda> createAgenda(@Valid
            @RequestBody AgendaRequest agendaRequest) {
        return agendaService.registerAgenda(agendaRequest);
    }
}
