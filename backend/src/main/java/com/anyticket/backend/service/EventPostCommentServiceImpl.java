package com.anyticket.backend.service;

import com.anyticket.backend.domain.EventPostComment;
import com.anyticket.backend.dto.EventPostCommentDto;
import com.anyticket.backend.repository.EventPostCommentRepository;
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
public class EventPostCommentServiceImpl implements EventPostCommentService {

    private static final int MAX_PAGE_SIZE = 100;

    private final EventPostCommentRepository eventPostCommentRepository;

    @Autowired
    public EventPostCommentServiceImpl(EventPostCommentRepository eventPostCommentRepository) {
        this.eventPostCommentRepository = eventPostCommentRepository;
    }

    @Override
    public List<EventPostCommentDto> findAll(int pageNumber, int pageSize, String sortBy) {
        Pageable pageable = PageRequest.of(pageNumber, pageSize, Sort.by(sortBy));
        Page<EventPostComment> eventPostComments = eventPostCommentRepository.findAll(pageable);
        if (eventPostComments.hasContent()) {
            return eventPostComments.getContent().stream().map(EventPostCommentDto::new).collect(Collectors.toList());
        }
        return Collections.emptyList();
    }

    @Override
    public List<EventPostCommentDto> findByEventPostId(long id, int pageNumber, int pageSize, String sortBy) {
        Pageable pageable = PageRequest.of(pageNumber, pageSize, Sort.by(sortBy));
        Page<EventPostComment> eventPostComments = eventPostCommentRepository.findByEventPostId(id, pageable);
        if (eventPostComments.hasContent()) {
            return eventPostComments.getContent().stream().map(EventPostCommentDto::new).collect(Collectors.toList());
        }
        return Collections.emptyList();
    }

    @Override
    @Transactional
    public Optional<EventPostCommentDto> save(EventPostComment eventPostComment) {
        try {
            return Optional.of(new EventPostCommentDto(eventPostCommentRepository.save(eventPostComment)));
        } catch (Exception ignored) { }
        return Optional.empty();
    }

}
