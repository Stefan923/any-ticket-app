package com.anyticket.backend.domain;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

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
    @CreationTimestamp
    private LocalDateTime creationTime;

    @Column(name = "last_update_time")
    @UpdateTimestamp
    private LocalDateTime lastUpdateTime;

    @OneToOne
    @MapsId
    @JoinColumn(name = "event_id")
    private Event event;

    @OneToMany(mappedBy = "eventPost", cascade = CascadeType.ALL, orphanRemoval = true)
    @ToString.Exclude
    private List<EventPostComment> comments;

}
