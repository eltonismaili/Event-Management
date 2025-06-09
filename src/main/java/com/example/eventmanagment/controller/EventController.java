package com.example.eventmanagment.controller;


import com.example.eventmanagment.dto.event.CreateEventRequest;
import com.example.eventmanagment.dto.event.EventDto;
import com.example.eventmanagment.dto.event.UpdateEventRequest;
import com.example.eventmanagment.helpers.FileHelper;
import com.example.eventmanagment.service.EventService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/events")
@PreAuthorize("hasAnyRole('ADMIN')")
public class EventController {
    private final EventService eventService;
    private final FileHelper fileHelper;

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

//        try {
//            if (!file.isEmpty()) {
//                String fileName = fileHelper.uploadFile(
//                        "target/classes/static/assets/files/images",
//                        file.getOriginalFilename(),
//                        file.getBytes()
//                );
//                request.setImageUrl("/assets/files/images/" + fileName);
//            }
//        } catch (IOException e) {
//            throw new RuntimeException("File upload failed: " + e.getMessage());
//        }
        EventDto created = eventService.create(request);
        return ResponseEntity.ok(created);
    }

    // PUT update event
    @PutMapping("/{id}")
    public ResponseEntity<EventDto> updateEvent(@PathVariable Long id,
                                                @RequestBody @Valid UpdateEventRequest request) {
//        try {
//            if (!file.isEmpty()) {
//                String fileName = fileHelper.uploadFile(
//                        "target/classes/static/assets/files/images",
//                        file.getOriginalFilename(),
//                        file.getBytes()
//                );
//                request.setImageUrl("/assets/files/images/" + fileName);
//            }
//        } catch (IOException e) {
//            throw new RuntimeException("File upload failed: " + e.getMessage());
//        }
        EventDto updated = eventService.update(id, request);
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
}
