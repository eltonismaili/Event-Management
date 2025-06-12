package com.example.eventmanagment.service.impls;

import com.example.eventmanagment.dto.event.CreateEventRequest;
import com.example.eventmanagment.dto.event.EventDto;
import com.example.eventmanagment.dto.event.UpdateEventRequest;
import com.example.eventmanagment.entities.*;
import com.example.eventmanagment.exceptions.category.CategoryNotFoundException;
import com.example.eventmanagment.exceptions.event.EventNotFoundException;
import com.example.eventmanagment.exceptions.user.UserNotFoundException;
import com.example.eventmanagment.exceptions.venue.VenueNotFoundException;
import com.example.eventmanagment.mapper.EventMapper;
import com.example.eventmanagment.repository.CategoryRepository;
import com.example.eventmanagment.repository.EventRepository;
import com.example.eventmanagment.repository.UserRepository;
import com.example.eventmanagment.repository.VenueRepository;
import com.example.eventmanagment.service.EventService;
import lombok.RequiredArgsConstructor;
import org.springframework.cglib.core.Local;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class EventServiceImpl implements EventService {

    private final EventRepository eventRepository;
    private final EventMapper eventMapper;
    private final CategoryRepository categoryRepository;
    private final VenueRepository venueRepository;
    private final UserRepository userRepository;

    @Override
    public List<EventDto> findAll() {
        return eventRepository.findAll()
                .stream()
                .map(eventMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public EventDto findById(Long id) {
        Event event = eventRepository.findById(id)
                .orElseThrow(() -> new EventNotFoundException(id));
        return eventMapper.toDto(event);
    }

    @Override
    public EventDto create(CreateEventRequest request, MultipartFile imageFile) {
        var event = eventMapper.toEntityCreate(request);
        Category category = categoryRepository.findById(request.getCategoryId())
                .orElseThrow(() -> new CategoryNotFoundException(request.getCategoryId()));


        Venue venue = venueRepository.findById(request.getVenueId())
                .orElseThrow(() -> new VenueNotFoundException(request.getVenueId()));

        var fileName = "";
        if (imageFile != null && !imageFile.isEmpty()) {
            fileName = uploadFile(imageFile);
        }

        event.setImagePath(fileName);


        event.setCreatedBy(AuthenticationServiceImpl.getLoggedInUserEmail());
        event.setCreatedAt(LocalDateTime.now());


        event.setCategory(category);
        event.setVenue(venue);


        return eventMapper.toDto(eventRepository.save(event));
    }

    @Override
    public EventDto update(Long id, UpdateEventRequest request, MultipartFile imageFile) {

        Event existing = eventRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Event not found with id: " + id));

        var fileName = "";

        if (imageFile != null && !imageFile.isEmpty()) fileName = uploadFile(imageFile);

        eventMapper.updateEntityFromDto(request, existing);

        existing.setUpdatedBy(AuthenticationServiceImpl.getLoggedInUserEmail());
        existing.setUpdatedAt(LocalDateTime.now());

        Category category = categoryRepository.findById(request.getCategory())
                .orElseThrow(() -> new CategoryNotFoundException(request.getCategory()));
        existing.setCategory(category);
        Venue venue = venueRepository.findById(request.getVenue())
                .orElseThrow(() -> new VenueNotFoundException(request.getVenue()));
        existing.setVenue(venue);

        if (fileName.isBlank()) {
            existing.setImagePath(existing.getImagePath()); // KEEP existing
        } else {
            existing.setImagePath(fileName); // SET new
        }

        System.out.println("updated req" + existing.getUpdatedBy());
        Event updated = eventRepository.save(existing);


        return eventMapper.toDto(updated);
    }

    public String uploadFile(MultipartFile imageFile) {
        String filename = UUID.randomUUID() + "_" + imageFile.getOriginalFilename();
        Path uploadDir = Paths.get("uploads");
        try {
            Files.createDirectories(uploadDir); // Create uploads/ if it doesn't exist
            Path imagePath = uploadDir.resolve(filename);
            Files.write(imagePath, imageFile.getBytes());
            return "uploads/" + filename;  // Return the path to the image file
        } catch (IOException e) {
            throw new RuntimeException("Failed to store image file", e);
        }
    }

    @Override
    public void delete(Long id) {
        if (!eventRepository.existsById(id)) {
            throw new RuntimeException("Event not found with id: " + id);
        }
        eventRepository.deleteById(id);
    }

    @Override
    public void deleteExpiredEvents() {
        LocalDateTime now = LocalDateTime.now();
        List<Event> expiredEvents = eventRepository.findByEndDateBefore(now);

        if (!expiredEvents.isEmpty()) {
            eventRepository.deleteAll(expiredEvents);
            System.out.println("Deleted " + expiredEvents.size() + " expired events at " + now);
        }
    }
    @Scheduled(fixedRate = 3600000)
    public void scheduledExpiredEventCleanup() {
        deleteExpiredEvents();
    }
}
