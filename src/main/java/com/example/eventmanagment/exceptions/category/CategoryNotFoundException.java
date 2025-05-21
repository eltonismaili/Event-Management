package com.example.eventmanagment.exceptions.category;

import com.example.eventmanagment.exceptions.ResourceNotFoundException;

public class CategoryNotFoundException extends ResourceNotFoundException {
    public CategoryNotFoundException(Long id) {

        super("Category with id " + id + " not found");
    }
}
