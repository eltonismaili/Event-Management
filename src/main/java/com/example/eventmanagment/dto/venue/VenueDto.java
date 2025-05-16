package com.example.eventmanagment.dto.venue;

import com.example.eventmanagment.dto.address.AddressDto;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class VenueDto {
    private Long id;
    @Size(min = 2, max = 50,message = "Name must be between 2 and 50 characters")
    @NotNull
        private String name;
    @NotNull
    private AddressDto address;
    @NotNull
    private int capacity;
}
