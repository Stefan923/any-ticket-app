package com.anyticket.backend.service;

import com.anyticket.backend.domain.Event;

import java.util.List;

public interface EventService {

    List<Event> findAll(int pageNumber, int pageSize, String sortBy);

}
