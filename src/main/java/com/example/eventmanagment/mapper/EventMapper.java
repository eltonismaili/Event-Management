package com.example.eventmanagment.mapper;

import com.example.eventmanagment.dto.event.CreateEventRequest;
import com.example.eventmanagment.dto.event.EventDto;
import com.example.eventmanagment.dto.event.UpdateEventRequest;
import com.example.eventmanagment.entities.Category;
import com.example.eventmanagment.entities.Event;
import com.example.eventmanagment.entities.Venue;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "spring", uses = {UserMapper.class, CategoryMapper.class, VenueMapper.class})
public interface EventMapper {

    @Mapping(source = "category.id", target = "categoryId")
    @Mapping(source = "venue.id", target = "venueId")
    EventDto toDto(Event event);

    Event toEntity(EventDto dto);



    Event toEntityCreate(CreateEventRequest request);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "category", source = "category")
    @Mapping(target = "venue", source = "venue")
    void updateEntityFromDto(UpdateEventRequest dto, @MappingTarget Event event);


    List<EventDto> toDtoList(List<Event> events);

    default Category mapCategory(Long categoryId) {
        if (categoryId == null) {
            return null;
        }
        Category category = new Category();
        category.setId(categoryId);
        return category;
    }

    default Venue mapVenue(Long venueId) {
        if (venueId == null) {
            return null;
        }
        Venue venue = new Venue();
        venue.setId(venueId);
        return venue;
    }

}

