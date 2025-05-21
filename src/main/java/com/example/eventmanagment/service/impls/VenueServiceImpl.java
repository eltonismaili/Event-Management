package com.example.eventmanagment.service.impls;

import com.example.eventmanagment.dto.venue.VenueDto;
import com.example.eventmanagment.entities.Address;
import com.example.eventmanagment.exceptions.address.AddressNotFoundException;
import com.example.eventmanagment.exceptions.venue.VenueNotFoundException;
import com.example.eventmanagment.mapper.VenueMapper;
import com.example.eventmanagment.repository.AddressRepository;
import com.example.eventmanagment.repository.VenueRepository;
import com.example.eventmanagment.service.VenueService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class VenueServiceImpl implements VenueService {

    private final VenueRepository venueRepository;
    private final VenueMapper venueMapper;
    private final AddressRepository addressRepository;


    @Override
    public List<VenueDto> findAll() {
        var venues = venueRepository.findAll();
        return venueMapper.toDtoList(venues);
    }

    @Override
    public VenueDto findById(Long id) {
        var venue = venueRepository.findById(id)
                .orElseThrow(() -> new VenueNotFoundException(id));
        return venueMapper.toDto(venue);
    }

    @Override
    public VenueDto create(VenueDto venueDto) {
        var venue = venueMapper.toEntity(venueDto);

        Address address = venue.getAddress();
        Address savedAddress = addressRepository.save(address);


        venue.setAddress(savedAddress);

        // Save the venue
        var savedVenue = venueRepository.save(venue);

        return venueMapper.toDto(savedVenue);
    }

    @Override
    public VenueDto update(Long id, VenueDto venueDto) {
        if (!venueRepository.existsById(id)) {
            throw new VenueNotFoundException("Venue not found with id: " + id);
        }

        var venue = venueMapper.toEntity(venueDto);

        Address address = addressRepository.findById(venueDto.getAddress().getId())
                .orElseThrow(() -> new AddressNotFoundException(venueDto.getAddress().getId()));

        venue.setAddress(address);
        var updatedVenue = venueRepository.save(venue);

        return venueMapper.toDto(updatedVenue);
    }

    @Override
    public void delete(Long id) {
        if (!venueRepository.existsById(id)) {
            throw new VenueNotFoundException(id);
        }
        venueRepository.deleteById(id);
    }
}
