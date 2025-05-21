package com.example.eventmanagment.exceptions.review;

import com.example.eventmanagment.exceptions.ResourceNotFoundException;

public class ReviewNotFoundException extends ResourceNotFoundException {
    public ReviewNotFoundException(Long id) {
        super("Review with id " + id + " not found");
    }

    public ReviewNotFoundException(String message) {
        super(message);
    }

}
