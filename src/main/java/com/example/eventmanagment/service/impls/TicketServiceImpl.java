package com.example.eventmanagment.service.impls;

import com.example.eventmanagment.dto.ticket.TicketDto;
import com.example.eventmanagment.entities.Event;
import com.example.eventmanagment.entities.Ticket;
import com.example.eventmanagment.entities.User;
import com.example.eventmanagment.exceptions.event.EventNotFoundException;
import com.example.eventmanagment.exceptions.ticket.TicketNotFoundException;
import com.example.eventmanagment.exceptions.user.UserNotFoundException;
import com.example.eventmanagment.mapper.TicketMapper;
import com.example.eventmanagment.repository.EventRepository;
import com.example.eventmanagment.repository.TicketRepository;
import com.example.eventmanagment.repository.UserRepository;
import com.example.eventmanagment.service.TicketService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TicketServiceImpl implements TicketService {
    private final TicketRepository ticketRepository;
    private final TicketMapper ticketMapper;
    private final EventRepository eventRepository;
    private final UserRepository userRepository;
    private final EmailService emailService;
//    mail sender


    @Override
    public List<TicketDto> findAll() {
        var tickets = ticketRepository.findAll();
        return ticketMapper.toDtoList(tickets);
    }

    @Override
    public TicketDto findById(Long id) {
        var ticket = ticketRepository.findById(id)
                .orElseThrow(() -> new TicketNotFoundException(id));
        return ticketMapper.toDto(ticket);
    }

    @Override
    public TicketDto create(TicketDto ticketDto) {
        Ticket ticket = ticketMapper.toEntity(ticketDto);

        Event event = eventRepository.findById(ticketDto.getEventId())
                .orElseThrow(() -> new EventNotFoundException(ticketDto.getEventId()));

        User user = userRepository.findById(ticketDto.getUserId())
                .orElseThrow(() -> new UserNotFoundException(ticketDto.getUserId()));

        int totalSold = ticketRepository.totalTicketsSoldForEvent(event.getId());

        int userTickets = ticketRepository.totalTicketsByUserForEvent(event.getId(), user.getId());

        int requestedQuantity = ticketDto.getQuantity();

        if (totalSold + requestedQuantity > event.getVenue().getCapacity()) {
            throw new IllegalStateException("Tickets sold out: Venue capacity reached.");
        }


        if (userTickets + requestedQuantity >= 3) {
            throw new IllegalStateException("Cannot buy more than 3 tickets per user for this event.");
        }

        ticket.setEvent(event);
        ticket.setUser(user);


        Ticket savedTicket = ticketRepository.save(ticket);
        String subject = "Ticket Confirmation for " + event.getName();
        String message = "Dear " + user.getName() + ",\n\n" +
                "Thank you for purchasing " + requestedQuantity + " ticket(s) for the event '" + event.getName() + "'.\n" +
                "Event Date: " + event.getStartDate() + "\n" +
                "Venue: " + event.getVenue().getName() + "\n\n" +
                "Enjoy the event!\n\nBest regards,\nEvent Management Team";

        emailService.sendTicketConfirmation(user.getEmail(), subject, message);

        return ticketMapper.toDto(savedTicket);
    }


    @Override
    public TicketDto update(Long id, TicketDto ticketDto) {
        if (!ticketRepository.existsById(id)) {
            throw new TicketNotFoundException(id);
        }

        if (!id.equals(ticketDto.getId())) {
            throw new IllegalArgumentException("ID in path and ticketDto must be the same");
        }

        Ticket ticket = ticketMapper.toEntity(ticketDto);

        Event event = eventRepository.findById(ticketDto.getEventId())
                .orElseThrow(() -> new EventNotFoundException(ticketDto.getEventId()));

        User user = userRepository.findById(ticketDto.getUserId())
                .orElseThrow(() -> new UserNotFoundException(ticketDto.getUserId()));

        ticket.setEvent(event);
        ticket.setUser(user);

        Ticket updatedTicket = ticketRepository.save(ticket);
        return ticketMapper.toDto(updatedTicket);
    }

    @Override
    public void delete(Long id) {
        if (!ticketRepository.existsById(id)) {
            throw new TicketNotFoundException(id);
        }
        ticketRepository.deleteById(id);
    }

    @Override
    public List<TicketDto> findAllByUserId(Long userId) {
        List<Ticket> tickets = ticketRepository.findAllByUserId(userId);
        return tickets.stream()
                .map(ticketMapper::toDto)
                .collect(Collectors.toList());
    }
}
