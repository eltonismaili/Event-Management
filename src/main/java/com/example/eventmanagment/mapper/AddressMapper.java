package com.example.eventmanagment.mapper;

import com.example.eventmanagment.dto.address.AddressDto;
import com.example.eventmanagment.entities.Address;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface AddressMapper {
    AddressDto toDto(Address address);
    Address toEntity(AddressDto addressDto);


    List<AddressDto> toDtoList(List<Address> addresses);
    List<Address> toEntityList(List<AddressDto> addressDtos);




}
