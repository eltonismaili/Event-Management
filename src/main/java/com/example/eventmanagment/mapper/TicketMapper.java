package com.example.eventmanagment.mapper;

import com.example.eventmanagment.dto.ticket.TicketDto;
import com.example.eventmanagment.entities.Ticket;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface TicketMapper {
    @Mapping(source = "user.id", target = "userId")
    @Mapping(source = "event.id", target = "eventId")
    TicketDto toDto(Ticket ticket);

    @Mapping(source = "userId", target = "user.id")
    @Mapping(source = "eventId", target = "event.id")
    Ticket toEntity(TicketDto ticketDto);

    List<TicketDto> toDtoList(List<Ticket> tickets);

    List<Ticket> toEntityList(List<TicketDto> ticketDtos);
}
