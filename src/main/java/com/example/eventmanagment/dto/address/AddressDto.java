package com.example.eventmanagment.dto.address;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddressDto {

    private Long id;
    @Size(min = 5, max = 5)
    @NotNull
    @NotBlank
    private String zipCode;
    @Size(min = 2, max = 50)
    @NotNull
    @NotBlank
    private String city;
    @Size(min = 2, max = 50)
    @NotNull
    @NotBlank
    private String country;
    @Size(min = 2, max = 50)
    @NotNull
    @NotBlank
    private String street;
}
