package com.example.eventmanagment.exceptions.venue;

import com.example.eventmanagment.exceptions.ResourceNotFoundException;

public class VenueNotFoundException extends ResourceNotFoundException {

    public VenueNotFoundException(String message) {
        super(message);
    }

    public VenueNotFoundException(Long id) {
        super("Venue with id " + id + " not found");
    }

    public VenueNotFoundException(String name, String address) {
        super("Venue with name " + name + " and address " + address + " not found");
    }
}
