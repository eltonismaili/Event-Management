package com.example.eventmanagment.service;

import com.example.eventmanagment.dto.registration.RegistrationDto;
import com.example.eventmanagment.entities.Registration;

import java.util.List;

public interface RegistrationService {

    List<RegistrationDto> findAll();

    RegistrationDto findById(Long id);

    RegistrationDto create(RegistrationDto registrationDto);

    RegistrationDto update(Long id, RegistrationDto registrationDto);

    void delete(Long id);
}
