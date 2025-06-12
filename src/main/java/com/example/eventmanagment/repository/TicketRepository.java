package com.example.eventmanagment.repository;

import com.example.eventmanagment.entities.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TicketRepository extends JpaRepository<Ticket, Long> {
    @Query("SELECT COALESCE(SUM(t.quantity), 0) FROM Ticket t WHERE t.event.id = :eventId")
    int totalTicketsSoldForEvent(@Param("eventId") Long eventId);

    @Query("SELECT COALESCE(SUM(t.quantity), 0) FROM Ticket t WHERE t.event.id = :eventId AND t.user.id = :userId")
    int totalTicketsByUserForEvent(@Param("eventId") Long eventId, @Param("userId") Long userId);

    List<Ticket> findAllByUserId(Long userId);
}
