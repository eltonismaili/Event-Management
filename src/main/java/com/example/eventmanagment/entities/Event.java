package com.example.eventmanagment.entities;

import com.example.eventmanagment.entities.enums.EventType;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "events")
public class Event {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Event name is required.")
    @Size(max = 255, message = "Event name must be less than 255 characters.")
    @Column(nullable = false)
    private String name;

    @NotBlank(message = "Event description is required.")
    @Size(max = 1000, message = "Description must be less than 1000 characters.")
    @Column(nullable = false, length = 1000)
    private String description;


    @ManyToOne
    @JoinColumn(name = "category_id", nullable = false)
    private Category category;

    @ManyToOne
    @JoinColumn(name = "venue_id", nullable = false)
    private Venue venue;

    @NotNull(message = "Start date is required.")
    @FutureOrPresent(message = "Start date must be in the present or future.")
    @Column(nullable = false)
    private LocalDateTime startDate;

    @Column(name = "image_path", length = 255, nullable = true)
    private String imagePath;

    @NotNull(message = "End date is required.")
    @Future(message = "End date must be in the future.")
    @Column(nullable = false)
    private LocalDateTime endDate;


    @NotNull(message = "Event type is required.")
    @Enumerated(EnumType.STRING)
    @Column(name = "event_type", nullable = false)
    private EventType eventType;

    @PositiveOrZero(message = "Ticket price must be zero or positive.")
    @Column(nullable = false)
    private double ticketPrice;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "created_by")
    private String createdBy;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @Column(name = "updated_by")
    private String updatedBy;
}