package com.example.eventmanagment.controller;

import com.example.eventmanagment.dto.venue.VenueDto;
import com.example.eventmanagment.service.VenueService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/venues")
public class VenueController {

    private final VenueService venueService;

    @GetMapping
    public ResponseEntity<List<VenueDto>> getAllVenues() {
        return ResponseEntity.ok(venueService.findAll());
    }

    @PostMapping
    public ResponseEntity<VenueDto> createVenue(@RequestBody @Valid VenueDto venueDto) {
        return ResponseEntity.ok(venueService.create(venueDto));
    }

    @GetMapping("/{id}")
    public ResponseEntity<VenueDto> getVenueById(@PathVariable Long id) {
        return ResponseEntity.ok(venueService.findById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<VenueDto> updateVenue(@PathVariable Long id, @RequestBody VenueDto venueDto) {
        return ResponseEntity.ok(venueService.update(id, venueDto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteVenue(@PathVariable Long id) {
        venueService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
