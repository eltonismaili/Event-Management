package com.example.eventmanagment.exceptions.address;

import com.example.eventmanagment.exceptions.ResourceNotFoundException;

public class AddressNotFoundException extends ResourceNotFoundException {
    public AddressNotFoundException(Long id) {
        super("Address with id " + id + " not found");
    }
}
