package com.example.eventmanagment.mapper;

import com.example.eventmanagment.dto.ticket.TicketDto;
import com.example.eventmanagment.entities.Ticket;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface TicketMapper {
    @Mapping(source = "user", target = "userId")
    @Mapping(source = "event", target = "eventId")
    TicketDto toDto(Ticket ticket);

    @Mapping(source = "userId", target = "user")
    @Mapping(source = "eventId", target = "event")
    Ticket toEntity(TicketDto ticketDto);

    List<TicketDto> toDtoList(List<Ticket> tickets);

    List<Ticket> toEntityList(List<TicketDto> ticketDtos);
}
