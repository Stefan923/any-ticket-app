package com.anyticket.backend.controller;

import com.anyticket.backend.domain.Event;
import com.anyticket.backend.dto.EventDto;
import com.anyticket.backend.service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.UnexpectedRollbackException;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin
@RestController
@RequestMapping("/api/events")
public class EventController {

    private final EventService eventService;

    @Autowired
    public EventController(EventService eventService) {
        this.eventService = eventService;
    }

    @GetMapping("")
    public ResponseEntity<List<EventDto>> getEvents(
            @RequestParam(defaultValue = "0") int pageNumber,
            @RequestParam(defaultValue = "10") int pageSize,
            @RequestParam(defaultValue = "id") String sortBy
    ) {
        return new ResponseEntity<>(eventService.findAll(pageNumber, pageSize, sortBy), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getEvent(@PathVariable long id) {
        Optional<EventDto> event = eventService.find(id);
        return event.isPresent() ? new ResponseEntity<>(event.get(), HttpStatus.FOUND) :
                new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    @PostMapping("")
    public ResponseEntity<?> createEvent(@RequestBody Event event) {
        try {
            Optional<EventDto> createdEvent = eventService.save(event);
            if (createdEvent.isPresent()) {
                return new ResponseEntity<>(createdEvent.get(), HttpStatus.CREATED);
            }
        } catch (UnexpectedRollbackException ignored) {
        } catch (Exception ex) {
            ex.printStackTrace();
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(null, HttpStatus.CONFLICT);
    }

}
