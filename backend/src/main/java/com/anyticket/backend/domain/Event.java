package com.anyticket.backend.domain;

import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@ToString
@Entity(name = "event")
@RequiredArgsConstructor
public class Event {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @ToString.Exclude
    private User organizer;

}
