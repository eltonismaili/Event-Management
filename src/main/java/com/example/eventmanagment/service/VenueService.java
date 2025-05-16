package com.example.eventmanagment.service;

import com.example.eventmanagment.dto.venue.VenueDto;

import java.util.List;

public interface VenueService {
    List<VenueDto> findAll();
    VenueDto findById(Long id);
    VenueDto create(VenueDto venueDto);
    VenueDto update(Long id, VenueDto venueDto);
    void delete(Long id);
}
