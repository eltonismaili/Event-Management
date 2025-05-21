package com.example.eventmanagment.exceptions.ticket;

import com.example.eventmanagment.exceptions.ResourceNotFoundException;

public class TicketNotFoundException extends ResourceNotFoundException {

    public TicketNotFoundException(String message) {
        super(message);
    }

    public TicketNotFoundException(Long id) {
        super("Ticket with id " + id + " not found");
    }

}
