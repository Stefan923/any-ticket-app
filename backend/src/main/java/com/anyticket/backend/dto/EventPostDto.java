package com.anyticket.backend.dto;

import com.anyticket.backend.domain.EventPost;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;

@Data
@RequiredArgsConstructor
public class EventPostDto {

    private long id;
    private String title;
    private String description;
    private LocalDateTime creationTime;
    private LocalDateTime lastUpdateTime;

    public EventPostDto(EventPost eventPost) {
        this.id = eventPost.getId();
        this.title = eventPost.getTitle();
        this.description = eventPost.getDescription();
        this.creationTime = eventPost.getCreationTime();
        this.lastUpdateTime = eventPost.getLastUpdateTime();
    }

}
