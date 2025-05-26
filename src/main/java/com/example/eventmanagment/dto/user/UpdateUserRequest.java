package com.example.eventmanagment.dto.user;

import com.example.eventmanagment.dto.address.AddressDto;

import com.example.eventmanagment.entities.enums.Role;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class UpdateUserRequest {
    @Size(min = 2, max = 50, message = "Name must be between 2 and 50 characters")
    @NotNull
    @NotBlank
    private String name;
    @Size(min = 2, max = 50, message = "Surname must be between 2 and 50 characters")
    @NotNull
    @NotBlank
    private String surname;
    @Size(min = 2, max = 50, message = "Email must be between 2 and 50 characters")
    @NotNull
    @NotBlank
    private String email;
    @Size(min = 2, max = 50, message = "Password must be between 2 and 50 characters")
    @NotNull
    @NotBlank
    private String password;
    @NotBlank
    @NotNull
    private int age;
    @NotBlank
    @NotNull
    private Role roles;
    @NotBlank
    @NotNull
    private AddressDto addressId;
    @Size(min = 2, max = 50, message = "Created by must be between 2 and 50 characters")
    @NotNull
    @NotBlank
    private String updatedBy;

    private LocalDateTime updatedAt;
}
