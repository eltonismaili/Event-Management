package com.example.eventmanagment.mapper;

import com.example.eventmanagment.dto.event.CreateEventRequest;
import com.example.eventmanagment.dto.event.EventDto;
import com.example.eventmanagment.dto.event.UpdateEventRequest;
import com.example.eventmanagment.entities.Event;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "spring", uses = {UserMapper.class, CategoryMapper.class, VenueMapper.class})
public interface EventMapper {
    @Mapping(source = "user", target = "userId")
    @Mapping(source = "category", target = "categoryId")
    @Mapping(source = "venue", target = "venueId")
    EventDto toDto(Event event);

    Event toEntity(EventDto dto);

    @Mapping(source = "userId",     target = "user")
    @Mapping(source = "categoryId", target = "category")
    @Mapping(source = "venueId",    target = "venue")
    Event toEntityCreate(CreateEventRequest request);

    void updateEntityFromDto(UpdateEventRequest dto, @MappingTarget Event event);

    List<EventDto> toDtoList(List<Event> events);
}

