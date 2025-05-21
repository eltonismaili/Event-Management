package com.example.eventmanagment.exceptions.registration;

import com.example.eventmanagment.exceptions.ResourceNotFoundException;

public class RegistrationNotFoundException extends ResourceNotFoundException {
    public RegistrationNotFoundException(Long id) {
        super("Registration with id " + id + " not found");
    }

    public RegistrationNotFoundException(String message) {
        super(message);
    }
}
