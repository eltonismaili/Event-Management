package com.example.eventmanagment.controller;

import com.example.eventmanagment.dto.registration.RegistrationDto;
import com.example.eventmanagment.service.RegistrationService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/registrations")
@RequiredArgsConstructor
public class RegistrationController {
    @Autowired
    private final RegistrationService registrationService;

    @GetMapping
    public ResponseEntity<List<RegistrationDto>> getAllRegistrations() {
        return ResponseEntity.ok(registrationService.findAll());
    }

    @PostMapping
    public ResponseEntity<RegistrationDto> createRegistration(@Valid @RequestBody RegistrationDto registrationDto) {
        return ResponseEntity.ok(registrationService.create(registrationDto));
    }

    @GetMapping("/{id}")
    public ResponseEntity<RegistrationDto> getRegistrationById(@PathVariable Long id) {
        return ResponseEntity.ok(registrationService.findById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<RegistrationDto> updateRegistration(@PathVariable Long id, @RequestBody RegistrationDto registrationDto) {
        return ResponseEntity.ok(registrationService.update(id, registrationDto));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<Void> deleteRegistration(@PathVariable Long id) {
        registrationService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
