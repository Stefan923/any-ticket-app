package com.anyticket.backend.dto;

import com.anyticket.backend.domain.Event;
import com.anyticket.backend.domain.EventPost;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class EventDto {

    private long id;
    private UserDto organizer;
    private EventPost eventPost;

    public EventDto(Event event) {
        this.id = event.getId();
        this.organizer = new UserDto(event.getOrganizer());
        this.eventPost = event.getEventPost();
    }

}
