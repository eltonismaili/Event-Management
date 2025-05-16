package com.example.eventmanagment.mapper;

import com.example.eventmanagment.dto.venue.VenueDto;
import com.example.eventmanagment.entities.Venue;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface VenueMapper {

    VenueDto toDto(Venue venue);

    Venue toEntity(VenueDto venueDto);

    List<VenueDto> toDtoList(List<Venue> venues);

    List<Venue> toEntityList(List<VenueDto> venueDtos);
}
