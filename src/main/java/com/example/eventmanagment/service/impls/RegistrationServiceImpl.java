package com.example.eventmanagment.service.impls;

import com.example.eventmanagment.dto.registration.RegistrationDto;
import com.example.eventmanagment.entities.Event;
import com.example.eventmanagment.entities.Registration;
import com.example.eventmanagment.entities.User;
import com.example.eventmanagment.exceptions.event.EventNotFoundException;
import com.example.eventmanagment.exceptions.registration.RegistrationNotFoundException;
import com.example.eventmanagment.exceptions.user.UserNotFoundException;
import com.example.eventmanagment.mapper.RegistrationMapper;
import com.example.eventmanagment.repository.EventRepository;
import com.example.eventmanagment.repository.RegistrationRepository;
import com.example.eventmanagment.repository.UserRepository;
import com.example.eventmanagment.service.RegistrationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RegistrationServiceImpl implements RegistrationService {
    private final RegistrationRepository registrationRepository;
    private final UserRepository userRepository;
    private final EventRepository eventRepository;
    private final RegistrationMapper registrationMapper;

    @Override
    public List<RegistrationDto> findAll() {
        var registrations = registrationRepository.findAll();
        return registrationMapper.toDtoList(registrations);
    }

    @Override
    public RegistrationDto findById(Long id) {
        var registration = registrationRepository.findById(id)
                .orElseThrow(() -> new RegistrationNotFoundException(id));
        return registrationMapper.toDto(registration);
    }

    @Override
    public RegistrationDto create(RegistrationDto dto) {

        var registration = registrationMapper.toEntity(dto);
        User user = userRepository.findById(dto.getUserId().getId())
                .orElseThrow(() -> new UserNotFoundException(dto.getUserId().getId()));
        registration.setUserId(user);
        Event event = eventRepository.findById(dto.getEventId().getId())
                .orElseThrow(() -> new EventNotFoundException(dto.getEventId().getId()));
        registration.setEventId(event);


        return registrationMapper.toDto(registrationRepository.save(registration));
    }

    @Override
    public RegistrationDto update(Long id, RegistrationDto dto) {
        if (!registrationRepository.existsById(id)) {
            throw new RuntimeException("Registration not found");
        }

        User user = userRepository.findById(dto.getUserId().getId())
                .orElseThrow(() -> new UserNotFoundException(dto.getUserId().getId()));

        Event event = eventRepository.findById(dto.getEventId().getId())
                .orElseThrow(() -> new EventNotFoundException(dto.getEventId().getId()));

        Registration registration = registrationMapper.toEntity(dto);
        registration.setId(id);
        registration.setUserId(user);
        registration.setEventId(event);

        Registration updated = registrationRepository.save(registration);
        return registrationMapper.toDto(updated);
    }

    @Override
    public void delete(Long id) {
        if (!registrationRepository.existsById(id)) {
            throw new RegistrationNotFoundException(id);
        }
        registrationRepository.deleteById(id);
    }
}
