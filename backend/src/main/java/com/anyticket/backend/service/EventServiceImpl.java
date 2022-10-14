package com.anyticket.backend.service;

import com.anyticket.backend.domain.Event;
import com.anyticket.backend.domain.EventPost;
import com.anyticket.backend.dto.EventDto;
import com.anyticket.backend.repository.EventPostRepository;
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

    private static final int MAX_PAGE_SIZE = 100;

    private final EventRepository eventRepository;
    private final EventPostRepository eventPostRepository;

    @Autowired
    public EventServiceImpl(EventRepository eventRepository, EventPostRepository eventPostRepository) {
        this.eventRepository = eventRepository;
        this.eventPostRepository = eventPostRepository;
    }

    @Override
    @Transactional
    public List<EventDto> findAll(int pageNumber, int pageSize, String sortBy) {
        Pageable pageable = PageRequest.of(pageNumber, Math.min(pageSize, MAX_PAGE_SIZE), Sort.by(sortBy));
        Page<Event> page = eventRepository.findAll(pageable);

        if (page.hasContent()) {
            return page.getContent().stream().map(EventDto::new).collect(Collectors.toList());
        }
        return Collections.emptyList();
    }

    @Override
    public Optional<EventDto> find(long id) {
        return eventRepository.findById(id).map(EventDto::new);
    }

    @Override
    @Transactional
    public Optional<EventDto> save(Event event) {
        try {
            EventPost eventPost = event.getEventPost();
            event.setEventPost(null);
            Event createdEvent = eventRepository.save(event);
            eventPost.setEvent(createdEvent);
            createdEvent.setEventPost(eventPostRepository.save(eventPost));
            return Optional.of(new EventDto(createdEvent));
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return Optional.empty();
    }

}
