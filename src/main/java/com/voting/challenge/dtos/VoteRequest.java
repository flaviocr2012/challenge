package com.voting.challenge.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.voting.challenge.enums.VoteStatus;
import lombok.*;


@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class VoteRequest {

    @JsonProperty(value = "associate_Id")
    private Long associateId;

    @JsonProperty(value = "session_Id")
    private Long session;

    @JsonProperty(value = "session_Id")
    private Long sessionId;

    @JsonProperty(value = "vote")
    private VoteStatus voteStatus;

}
