package com.anyticket.backend.service;

import com.anyticket.backend.domain.EventPostComment;
import com.anyticket.backend.dto.EventPostCommentDto;

import java.util.List;
import java.util.Optional;

public interface EventPostCommentService {

    List<EventPostCommentDto> findAll(int pageNumber, int pageSize, String sortBy);
    List<EventPostCommentDto> findByEventPostId(long id, int pageNumber, int pageSize, String sortBy);
    Optional<EventPostCommentDto> save(EventPostComment eventPostComment);

}
