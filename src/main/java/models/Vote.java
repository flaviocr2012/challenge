package models;

import enums.VoteStatus;
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


    private VoteId id;

    @OneToOne
    @JoinColumn(name = "associate_id")
    private Associate associate;

    @Enumerated(EnumType.STRING)
    private VoteStatus voteStatus;

    @ManyToOne
    @JoinColumn(name = "session_id")
    private Session session;

}
