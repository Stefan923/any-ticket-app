package com.anyticket.backend.service;

import com.anyticket.backend.domain.EventPost;
import com.anyticket.backend.dto.EventPostDto;
import com.anyticket.backend.repository.EventPostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class EventPostServiceImpl implements EventPostService {

    private static final int MAX_PAGE_SIZE = 100;

    private final EventPostRepository eventPostRepository;

    @Autowired
    public EventPostServiceImpl(EventPostRepository eventPostRepository) {
        this.eventPostRepository = eventPostRepository;
    }

    @Override
    public List<EventPostDto> findAll(int pageNumber, int pageSize, String sortBy) {
        Pageable pageable = PageRequest.of(pageNumber, Math.min(pageSize, MAX_PAGE_SIZE), Sort.by(sortBy));
        Page<EventPost> events = eventPostRepository.findAll(pageable);

        if (events.hasContent()) {
            return events.getContent().stream().map(EventPostDto::new).collect(Collectors.toList());
        }
        return Collections.emptyList();
    }

    @Override
    public Optional<EventPostDto> find(long id) {
        return eventPostRepository.findById(id).map(EventPostDto::new);
    }

    @Override
    @Transactional
    public Optional<EventPostDto> save(EventPost eventPost) {
        try {
            return Optional.of(new EventPostDto(eventPostRepository.save(eventPost)));
        } catch (Exception ex) {
            ex.printStackTrace();
            return Optional.empty();
        }
    }

}
