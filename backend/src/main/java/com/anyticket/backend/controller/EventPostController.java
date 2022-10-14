package com.anyticket.backend.controller;

import com.anyticket.backend.domain.EventPost;
import com.anyticket.backend.dto.EventPostDto;
import com.anyticket.backend.service.EventPostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.UnexpectedRollbackException;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@CrossOrigin
@RestController
@RequestMapping("/api/eventPosts")
public class EventPostController {

    private final EventPostService eventPostService;

    @Autowired
    public EventPostController(EventPostService eventPostService) {
        this.eventPostService = eventPostService;
    }

    @GetMapping("")
    public ResponseEntity<?> getEventPosts(
            @RequestParam(defaultValue = "0") int pageNumber,
            @RequestParam(defaultValue = "10") int pageSize,
            @RequestParam(defaultValue = "id") String sortBy
    ) {
        return new ResponseEntity<>(eventPostService.findAll(pageNumber, pageSize, sortBy), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getEventPostById(@PathVariable long id) {
        Optional<EventPostDto> eventPostDto = eventPostService.find(id);
        if (eventPostDto.isPresent()) {
            return new ResponseEntity<>(eventPostDto.get(), HttpStatus.FOUND);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    @PostMapping("")
    public ResponseEntity<?> createEventPost(@RequestBody EventPost eventPost) {
        try {
            Optional<EventPostDto> eventPostDto = eventPostService.save(eventPost);
            if (eventPostDto.isPresent()) {
                return new ResponseEntity<>(eventPostDto.get(), HttpStatus.CREATED);
            }
        } catch (UnexpectedRollbackException ex) {
            ex.printStackTrace();
        }
        return new ResponseEntity<>(null, HttpStatus.CONFLICT);
    }

}
