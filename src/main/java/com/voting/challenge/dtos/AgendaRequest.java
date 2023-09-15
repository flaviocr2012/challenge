package com.voting.challenge.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record AgendaRequest() {

    @NotBlank(message = "it should have a subject")
    @NotNull
    private static String subject;

}
