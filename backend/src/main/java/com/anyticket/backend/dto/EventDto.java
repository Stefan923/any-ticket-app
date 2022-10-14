package com.anyticket.backend.dto;

import com.anyticket.backend.domain.Event;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class EventDto {

    private long id;
    private UserDto organizer;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private EventPostDto eventPost;

    public EventDto(Event event) {
        this.id = event.getId();
        this.organizer = new UserDto(event.getOrganizer());
        this.eventPost = new EventPostDto(event.getEventPost());
    }

}
