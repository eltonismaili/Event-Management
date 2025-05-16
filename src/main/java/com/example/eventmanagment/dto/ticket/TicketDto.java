package com.example.eventmanagment.dto.ticket;

import com.example.eventmanagment.dto.event.EventDto;
import com.example.eventmanagment.dto.user.UserDto;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TicketDto {
    private Long id;
    @NotNull
    private int quantity;

    @NotNull
    private EventDto eventId;

    @NotNull
    private UserDto userId;
    @Size(min = 2, max = 50, message = "Name must be between 2 and 50 characters")

    @NotNull
    private String name;

    @NotNull
    private double price;

    @NotNull
    private LocalDateTime startDate;

    @NotNull
    private LocalDateTime endDate;
}
