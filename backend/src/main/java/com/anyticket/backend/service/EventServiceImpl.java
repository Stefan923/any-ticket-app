package com.anyticket.backend.service;

import com.anyticket.backend.domain.Event;
import com.anyticket.backend.dto.EventDto;
import com.anyticket.backend.repository.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class EventServiceImpl implements EventService {

    private final EventRepository eventRepository;

    @Autowired
    public EventServiceImpl(EventRepository eventRepository) {
        this.eventRepository = eventRepository;
    }

    @Override
    @Transactional
    public List<EventDto> findAll(int pageNumber, int pageSize, String sortBy) {
        Pageable pageable = PageRequest.of(pageNumber, pageSize, Sort.by(sortBy));
        Page<Event> page = eventRepository.findAll(pageable);

        if (page.hasContent()) {
            return page.getContent().stream().map(EventDto::new).collect(Collectors.toList());
        }
        return Collections.emptyList();
    }

    @Override
    @Transactional
    public Optional<EventDto> save(Event event) {
        try {
            return Optional.of(new EventDto(eventRepository.save(event)));
        } catch (Exception ignored) { }

        return Optional.empty();
    }

}
