package com.anyticket.backend.service;

import com.anyticket.backend.domain.EventPost;
import com.anyticket.backend.dto.EventPostDto;

import java.util.List;
import java.util.Optional;

public interface EventPostService {

    List<EventPostDto> findAll(int pageNumber, int pageSize, String sortBy);
    Optional<EventPostDto> find(long id);
    Optional<EventPostDto> save(EventPost eventPost);

}
