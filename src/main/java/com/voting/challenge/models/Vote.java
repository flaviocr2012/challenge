package com.voting.challenge.models;

import com.voting.challenge.enums.VoteStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Vote {

    @EmbeddedId
    private VoteId id;

    @Enumerated(EnumType.STRING)
    private VoteStatus voteStatus;

    @ManyToOne
    @JoinColumn(name = "session_id")
    private Session session;

}
