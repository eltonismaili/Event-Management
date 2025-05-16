package com.example.eventmanagment.dto.registration;

import com.example.eventmanagment.dto.event.EventDto;
import com.example.eventmanagment.dto.user.UserDto;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RegistrationDto {

    private Long id;
    @NotBlank
    @NotNull
    private UserDto userId;
    @NotBlank
    @NotNull
    private EventDto eventId;
}
