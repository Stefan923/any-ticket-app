package com.anyticket.backend.domain;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@Entity(name = "eventPost")
@Table(name = "event_posts")
@RequiredArgsConstructor
public class EventPost {

    @Id
    @Column(name = "event_id")
    private long id;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "description", length = 2048, nullable = false)
    private String description;

    @Column(name = "creation_time")
    private LocalDateTime creationTime;

    @Column(name = "last_update_time")
    private LocalDateTime lastUpdateTime;

    @OneToOne
    @MapsId
    @JoinColumn(name = "event_id")
    private Event event;

}
