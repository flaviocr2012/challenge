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
    @Column(unique = true)
    private VoteId id;

    @Enumerated(EnumType.STRING)
    private VoteStatus voteStatus;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "session",insertable = false, updatable = false)
    private Session session;

}
