package com.example.eventmanagment.controller;


import com.example.eventmanagment.dto.event.CreateEventRequest;
import com.example.eventmanagment.dto.event.EventDto;
import com.example.eventmanagment.dto.event.UpdateEventRequest;
import com.example.eventmanagment.entities.Event;
import com.example.eventmanagment.service.EventService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/events")
@PreAuthorize("hasAnyRole('ADMIN','USER')")
public class EventController {
    private final EventService eventService;

    // GET all events
    @GetMapping
    @PreAuthorize("hasAnyAuthority('admin:read','user:read')")
    public ResponseEntity<List<EventDto>> getAllEvents() {
        List<EventDto> events = eventService.findAll();
        return ResponseEntity.ok(events);
    }

    // GET event by ID
    @PreAuthorize("hasAnyAuthority('admin:read','user:read','user:write')")
    @GetMapping("/{id}")
    public ResponseEntity<EventDto> getEventById(@PathVariable Long id) {
        EventDto event = eventService.findById(id);
        return ResponseEntity.ok(event);
    }

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<EventDto> createEvent(@RequestPart("data") @Valid CreateEventRequest request,
                                                @RequestPart(value = "image", required = false) MultipartFile imageFile) {


        EventDto created = eventService.create(request, imageFile);
        return ResponseEntity.ok(created);
    }

    // PUT update event
    @PutMapping("/{id}")
    public ResponseEntity<EventDto> updateEvent(
            @PathVariable Long id,
            @RequestPart("data") @Valid UpdateEventRequest request,
            @RequestPart(value = "image", required = false) MultipartFile imageFile
    ) {

        EventDto updated = eventService.update(id, request, imageFile);
        return ResponseEntity.ok(updated);
    }

    // DELETE event
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEvent(@PathVariable Long id) {
        eventService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/default")
    public CreateEventRequest getDefaultEvent() {
        return new CreateEventRequest();
    }

    // Endpoint to delete expired events only in postman
//    @DeleteMapping("/cleanup")
//    public ResponseEntity<String> deleteExpiredEvents() {
//        eventService.deleteExpiredEvents();
//        return ResponseEntity.ok("Expired events deleted.");
//    }

    @GetMapping("/active")
    public List<Event> getActiveEvents() {
        return eventService.getActiveEvents();
    }
}
