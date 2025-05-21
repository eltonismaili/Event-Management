package com.example.eventmanagment.service.impls;

import com.example.eventmanagment.dto.event.CreateEventRequest;
import com.example.eventmanagment.dto.event.EventDto;
import com.example.eventmanagment.dto.event.UpdateEventRequest;
import com.example.eventmanagment.entities.Category;
import com.example.eventmanagment.entities.Event;
import com.example.eventmanagment.entities.User;
import com.example.eventmanagment.entities.Venue;
import com.example.eventmanagment.mapper.EventMapper;
import com.example.eventmanagment.repository.CategoryRepository;
import com.example.eventmanagment.repository.EventRepository;
import com.example.eventmanagment.repository.UserRepository;
import com.example.eventmanagment.repository.VenueRepository;
import com.example.eventmanagment.service.EventService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
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
                .orElseThrow(() -> new RuntimeException("Event not found with id: " + id));
        return eventMapper.toDto(event);
    }

    @Override
    public EventDto create(CreateEventRequest request) {
        var event = eventMapper.toEntityCreate(request);
        Category category = categoryRepository.findById(request.getCategoryId().getId())
                .orElseThrow(() -> new RuntimeException("Category not found"));

        User user = userRepository.findById(request.getUserId().getId())
                .orElseThrow(() -> new RuntimeException("User not found"));

        Venue venue = venueRepository.findById(request.getVenueId().getId())
                .orElseThrow(() -> new RuntimeException("Venue not found"));

        event.setCategory(category);
        event.setUser(user);
        event.setVenue(venue);


        return eventMapper.toDto(eventRepository.save(event));
    }

    @Override
    public EventDto update(Long id, UpdateEventRequest request) {
        Event existing = eventRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Event not found with id: " + id));

        eventMapper.updateEntityFromDto(request, existing);


        Event updated = eventRepository.save(existing);
        return eventMapper.toDto(updated);
    }

    @Override
    public void delete(Long id) {
        if (!eventRepository.existsById(id)) {
            throw new RuntimeException("Event not found with id: " + id);
        }
        eventRepository.deleteById(id);
    }
}
