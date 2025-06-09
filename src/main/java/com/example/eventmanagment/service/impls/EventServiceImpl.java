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
                .orElseThrow(() -> new EventNotFoundException(id));
        return eventMapper.toDto(event);
    }

    @Override
    public EventDto create(CreateEventRequest request) {
        var event = eventMapper.toEntityCreate(request);
//        Category category = categoryRepository.findById(request.getCategoryId().getId())
//                .orElseThrow(() -> new CategoryNotFoundException(request.getCategoryId().getId()));

        Category category = event.getCategory();
        Category savedCategory = categoryRepository.save(category);


        User user = userRepository.findById(request.getUserId().getId())
                .orElseThrow(() -> new UserNotFoundException(request.getUserId().getId()));


//        Venue venue = venueRepository.findById(request.getVenueId().getId())
//                .orElseThrow(() -> new VenueNotFoundException(request.getVenueId().getId()));

        Venue venue = event.getVenue();
        Venue savedVenue = venueRepository.save(venue);
//




        event.setCategory(savedCategory);
        event.setUser(user);
        event.setVenue(savedVenue);


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
