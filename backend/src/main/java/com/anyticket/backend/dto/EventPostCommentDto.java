package com.anyticket.backend.dto;

import com.anyticket.backend.domain.EventPostComment;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;

@Data
@RequiredArgsConstructor
public class EventPostCommentDto {

    private long id;
    private UserDto author;
    private String content;
    private LocalDateTime creationTime;
    private LocalDateTime lastUpdateTime;

    public EventPostCommentDto(EventPostComment eventPostComment) {
        this.id = eventPostComment.getId();
        this.author = new UserDto(eventPostComment.getAuthor());
        this.content = eventPostComment.getContent();
        this.creationTime = eventPostComment.getCreationTime();
        this.lastUpdateTime = eventPostComment.getLastUpdateTime();
    }

}
