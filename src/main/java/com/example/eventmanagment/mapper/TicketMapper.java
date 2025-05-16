package com.example.eventmanagment.mapper;

import com.example.eventmanagment.dto.ticket.TicketDto;
import com.example.eventmanagment.entities.Ticket;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface TicketMapper {
    TicketDto toDto(Ticket ticket);

    Ticket toEntity(TicketDto ticketDto);

    List<TicketDto> toDtoList(List<Ticket> tickets);

    List<Ticket> toEntityList(List<TicketDto> ticketDtos);
}
