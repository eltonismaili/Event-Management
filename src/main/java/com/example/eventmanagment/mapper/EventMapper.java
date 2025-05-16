package com.example.eventmanagment.mapper;

import com.example.eventmanagment.dto.event.CreateEventRequest;
import com.example.eventmanagment.dto.event.EventDto;
import com.example.eventmanagment.dto.event.UpdateEventRequest;
import com.example.eventmanagment.entities.Event;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "spring", uses = {UserMapper.class, CategoryMapper.class, VenueMapper.class})
public interface EventMapper {
    EventDto toDto(Event event);

    Event toEntity(EventDto eventDto);

    Event toEntityCreate(CreateEventRequest request);

    void updateEntityFromDto(UpdateEventRequest dto, @MappingTarget Event event);

    List<EventDto> toDtoList(List<Event> events);

    List<Event> toEntityList(List<EventDto> eventDtos);
}

