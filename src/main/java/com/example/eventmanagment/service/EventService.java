package com.example.eventmanagment.service;

import com.example.eventmanagment.dto.event.CreateEventRequest;
import com.example.eventmanagment.dto.event.EventDto;
import com.example.eventmanagment.dto.event.UpdateEventRequest;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface EventService {

    List<EventDto> findAll();

    EventDto findById(Long id);

    EventDto create(CreateEventRequest eventDto, MultipartFile imageFile);

    EventDto update(Long id, UpdateEventRequest eventDto, MultipartFile imageFile);

    void delete(Long id);

    void deleteExpiredEvents();
}
