package com.example.eventmanagment.exceptions.role;

import com.example.eventmanagment.exceptions.ResourceNotFoundException;

public class RoleNotFoundException extends ResourceNotFoundException {
    public RoleNotFoundException(Long id) {
        super("Role with " + id + " not found");
    }
}
