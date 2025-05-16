package com.example.eventmanagment.controller;

import com.example.eventmanagment.dto.address.AddressDto;
import com.example.eventmanagment.service.AddressService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/v1/addresses")
@RequiredArgsConstructor
public class AddressController {
    private final AddressService addressService;


    @GetMapping
    public ResponseEntity<List<AddressDto>> findAll() {
        return ResponseEntity.ok(addressService.findAll());
    }


    @GetMapping("/{id}")
    public ResponseEntity<AddressDto> findById(@PathVariable Long id) {
        return ResponseEntity.ok(addressService.findById(id));
    }

    @PostMapping
    public ResponseEntity<AddressDto> create(@RequestBody AddressDto addressDto) {
        var createdAddress = addressService.create(addressDto);
        return ResponseEntity.status(201).body(createdAddress);
    }

    @PutMapping("/{id}")
    public ResponseEntity<AddressDto> update(@PathVariable Long id, @RequestBody AddressDto addressDto) {
        return ResponseEntity.ok(addressService.update(id, addressDto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        addressService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
