package com.voting.challenge.models;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Session {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "agenda_id", unique = true)
    private Agenda agenda;

    @OneToMany(mappedBy = "session", fetch = FetchType.LAZY)
    private List<Vote> votes;


}
