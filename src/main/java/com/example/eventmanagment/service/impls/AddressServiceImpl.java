package com.example.eventmanagment.service.impls;


import com.example.eventmanagment.dto.address.AddressDto;
import com.example.eventmanagment.exceptions.address.AddressNotFoundException;
import com.example.eventmanagment.mapper.AddressMapper;
import com.example.eventmanagment.repository.AddressRepository;
import com.example.eventmanagment.service.AddressService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AddressServiceImpl implements AddressService {
    private final AddressRepository addressRepository;
    private final AddressMapper addressMapper;


    @Override
    public List<AddressDto> findAll() {
        var addresses = addressRepository.findAll();
        return addressMapper.toDtoList(addresses);
    }

    @Override
    public AddressDto findById(Long id) {
        var address = addressRepository.findById(id)
                .orElseThrow(() -> new AddressNotFoundException(id));
        return addressMapper.toDto(address);
    }

    @Override
    public AddressDto create(AddressDto addressDto) {
        var addresses = addressMapper.toEntity(addressDto);
        var savedAddresses = addressRepository.save(addresses);
        return addressMapper.toDto(savedAddresses);
    }

    public AddressDto update(Long id, AddressDto addressDto) {
        if (!addressRepository.existsById(id)) {
            throw new AddressNotFoundException(id);
        }
        var addresses = addressMapper.toEntity(addressDto);
        var updatedAddress = addressRepository.save(addresses);
        return addressMapper.toDto(updatedAddress);
    }

    @Override
    public void delete(Long id) {
        if (!addressRepository.existsById(id)) {
            throw new AddressNotFoundException(id);
        }
        addressRepository.deleteById(id);

    }
}
