package com.example.eventmanagment.service;

import com.example.eventmanagment.dto.event.CreateEventRequest;
import com.example.eventmanagment.dto.event.EventDto;
import com.example.eventmanagment.dto.event.UpdateEventRequest;

import java.util.List;

public interface EventService {

    List<EventDto> findAll();

    EventDto findById(Long id);

    EventDto create(CreateEventRequest eventDto);

    EventDto update(Long id, UpdateEventRequest eventDto);

    void delete(Long id);
}
