package com.example.eventmanagment.exceptions.event;

import com.example.eventmanagment.exceptions.ResourceNotFoundException;

public class EventNotFoundException extends ResourceNotFoundException {
    public EventNotFoundException(String message) {
        super(message);
    }

    public EventNotFoundException(Long id) {
        super("Event with id " + id + " not found");
    }

    public EventNotFoundException(String name, String location) {
        super("Event with name " + name + " and location " + location + " not found");
    }
}
