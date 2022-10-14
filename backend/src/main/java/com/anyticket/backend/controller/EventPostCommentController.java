package com.anyticket.backend.controller;

import com.anyticket.backend.service.EventPostCommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("/api/eventPostComments")
public class EventPostCommentController {

    private final EventPostCommentService eventPostCommentService;

    @Autowired
    public EventPostCommentController(EventPostCommentService eventPostCommentService) {
        this.eventPostCommentService = eventPostCommentService;
    }

    @GetMapping("/{eventPostId}")
    public ResponseEntity<?> getComments(
            @PathVariable("eventPostId") long eventPostId,
            @RequestParam(defaultValue = "0") int pageNumber,
            @RequestParam(defaultValue = "10") int pageSize,
            @RequestParam(defaultValue = "creationTime") String sortBy
    ) {
        return new ResponseEntity<>(
                eventPostCommentService.findByEventPostId(eventPostId, pageNumber, pageSize, sortBy),
                HttpStatus.OK
        );
    }

}
