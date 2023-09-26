package com.voting.challenge.controllers;


import com.voting.challenge.dtos.AgendaRequest;
import com.voting.challenge.models.Agenda;
import com.voting.challenge.services.AgendaService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/agenda")
@RequiredArgsConstructor
@CrossOrigin(origins = "*", maxAge = 3600)
public class AgendaController {

    private final AgendaService agendaService;

    @PostMapping(produces = "application/json")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Agenda> createAgenda(@Valid
            @RequestBody AgendaRequest agendaRequest) {
        return agendaService.registerAgenda(agendaRequest);
    }
}
