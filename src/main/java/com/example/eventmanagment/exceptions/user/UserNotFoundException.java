package com.example.eventmanagment.exceptions.user;

import com.example.eventmanagment.exceptions.ResourceNotFoundException;

public class UserNotFoundException extends ResourceNotFoundException {
    public UserNotFoundException(Long id) {
        super("User with id " + id + " not found");
    }
}
