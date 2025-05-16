package com.example.eventmanagment.service;


import com.example.eventmanagment.dto.address.AddressDto;

import java.util.List;

public interface AddressService {

    List<AddressDto> findAll();

    AddressDto findById(Long id);

    AddressDto create(AddressDto addressDto);

    AddressDto update(Long id, AddressDto addressDto);

    void delete(Long id);
}
