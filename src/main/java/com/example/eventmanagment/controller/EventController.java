package com.example.eventmanagment.controller;


import com.example.eventmanagment.dto.event.CreateEventRequest;
import com.example.eventmanagment.dto.event.EventDto;
import com.example.eventmanagment.dto.event.UpdateEventRequest;
import com.example.eventmanagment.service.EventService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/events")
@PreAuthorize("hasAnyRole('ADMIN')")
public class EventController {
    private final EventService eventService;

    // GET all events
    @GetMapping
    @PreAuthorize("hasAnyAuthority('admin:read')")
    public ResponseEntity<List<EventDto>> getAllEvents() {
        List<EventDto> events = eventService.findAll();
        return ResponseEntity.ok(events);
    }

    // GET event by ID
    @GetMapping("/{id}")
    public ResponseEntity<EventDto> getEventById(@PathVariable Long id) {
        EventDto event = eventService.findById(id);
        return ResponseEntity.ok(event);
    }

    // POST create event
    @PostMapping
    public ResponseEntity<EventDto> createEvent(@RequestBody @Valid CreateEventRequest request) {
        EventDto created = eventService.create(request);
        return ResponseEntity.ok(created);
    }

    // PUT update event
    @PutMapping("/{id}")
    public ResponseEntity<EventDto> updateEvent(@PathVariable Long id,
                                                @RequestBody @Valid UpdateEventRequest request) {
        EventDto updated = eventService.update(id, request);
        return ResponseEntity.ok(updated);
    }

    // DELETE event
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEvent(@PathVariable Long id) {
        eventService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
