package com.example.eventmanagment.repository;

import com.example.eventmanagment.entities.RegistrationTickets;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RegistrationTicketsRepository extends JpaRepository<RegistrationTickets, Long> {
}
