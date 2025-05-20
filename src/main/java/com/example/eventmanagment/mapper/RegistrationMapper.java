package com.example.eventmanagment.mapper;

import com.example.eventmanagment.dto.registration.RegistrationDto;
import com.example.eventmanagment.entities.Registration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface RegistrationMapper {
    @Mapping(source = "userId", target = "userId")
    @Mapping(source = "eventId", target = "eventId")
    RegistrationDto toDto(Registration registration);

    @Mapping(source = "userId", target = "userId")
    @Mapping(source = "eventId", target = "eventId")
    Registration toEntity(RegistrationDto dto);

    List<RegistrationDto> toDtoList(List<Registration> registrations);

    List<Registration> toEntityList(List<RegistrationDto> registrationDto);
}
