package com.voting.challenge.dtos;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.time.LocalDateTime;


@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SessionRequest {

    @JsonFormat(pattern = "dd/MM/yyyy hh:mm:ss")
    private LocalDateTime start = LocalDateTime.now();

    @JsonFormat(pattern = "dd/MM/yyyy hh:mm:ss")
    private LocalDateTime end = LocalDateTime.now().plusMinutes(1L);

    @JsonProperty(value = "id_session")
    private Long sessionId;
}
