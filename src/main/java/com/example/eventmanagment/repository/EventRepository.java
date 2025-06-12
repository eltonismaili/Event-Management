package com.example.eventmanagment.repository;

import com.example.eventmanagment.entities.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface EventRepository extends JpaRepository<Event, Long> {
    List<Event> findByEndDateBefore(LocalDateTime dateTime);
}
