package com.voting.challenge.dtos;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.time.LocalDateTime;


public record SessionRequest() {

    @JsonFormat(pattern = "dd/MM/yyyy hh:mm:ss")
    private static LocalDateTime start;

    @JsonProperty(value = "id_session")
    private static Long IdSession;
}
