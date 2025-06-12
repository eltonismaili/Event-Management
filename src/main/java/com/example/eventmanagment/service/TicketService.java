package com.example.eventmanagment.service;


import com.example.eventmanagment.dto.ticket.TicketDto;

import java.util.List;

public interface TicketService {

    List<TicketDto> findAll();

    TicketDto findById(Long id);

    TicketDto create(TicketDto ticketDto);

    TicketDto update(Long id, TicketDto ticketDto);

    void delete(Long id);

    List<TicketDto> findAllByUserId(Long userId);
}
