package com.example.eventmanagment.dto.registration_tickets;

import com.example.eventmanagment.dto.ticket.TicketDto;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RegistrationTicketsDto {
    private Long id;
    @NotBlank
    @NotNull
    private TicketDto ticketId;
}
