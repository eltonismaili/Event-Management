package com.example.eventmanagment.controller;

import com.example.eventmanagment.dto.ticket.TicketDto;
import com.example.eventmanagment.repository.TicketRepository;
import com.example.eventmanagment.service.TicketService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/tickets")
@RequiredArgsConstructor
public class TicketController {
    private final TicketService ticketService;
    private final   TicketRepository ticketRepository;

    @GetMapping
    public ResponseEntity<List<TicketDto>> getAllTickets() {
        return ResponseEntity.ok(ticketService.findAll());
    }

    @PostMapping
    public ResponseEntity<TicketDto> createTicket(@Valid @RequestBody TicketDto ticketDto) {
        return ResponseEntity.ok(ticketService.create(ticketDto));
    }

    @GetMapping("/{id}")
    public ResponseEntity<TicketDto> getTicketById(@PathVariable Long id) {
        return ResponseEntity.ok(ticketService.findById(id));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<Void> deleteTicket(@PathVariable Long id) {
        ticketService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<TicketDto> updateTicket(@PathVariable Long id, @Valid @RequestBody TicketDto ticketDto) {
        return ResponseEntity.ok(ticketService.update(id, ticketDto));
    }

    @GetMapping("/stats/{eventId}/user/{userId}")
    public ResponseEntity<Map<String, Integer>> getTicketStats(
            @PathVariable Long eventId,
            @PathVariable Long userId) {

        int totalSold = ticketRepository.totalTicketsSoldForEvent(eventId);
        int userTickets = ticketRepository.totalTicketsByUserForEvent(eventId, userId);

        Map<String, Integer> stats = new HashMap<>();
        stats.put("totalSold", totalSold);
        stats.put("userTickets", userTickets);

        return ResponseEntity.ok(stats);
    }

    @GetMapping("/user/{userId}")
    public List<TicketDto> getTicketsByUserId(@PathVariable Long userId) {
        return ticketService.findAllByUserId(userId);
    }


}
