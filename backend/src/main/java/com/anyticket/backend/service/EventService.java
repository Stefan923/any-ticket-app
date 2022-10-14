package com.anyticket.backend.service;

import com.anyticket.backend.domain.Event;
import com.anyticket.backend.dto.EventDto;

import java.util.List;
import java.util.Optional;

public interface EventService {

    List<EventDto> findAll(int pageNumber, int pageSize, String sortBy);
    Optional<EventDto> find(long id);
    Optional<EventDto> save(Event event);

}
