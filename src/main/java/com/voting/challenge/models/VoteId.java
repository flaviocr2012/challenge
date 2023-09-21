package com.voting.challenge.models;

import jakarta.persistence.Embeddable;
import lombok.*;

import java.io.Serializable;

@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@Embeddable
@Data
@Builder
public class VoteId implements Serializable {

    private Long associateId;
    private Long sessionId;

}
