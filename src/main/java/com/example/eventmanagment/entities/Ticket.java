    package com.example.eventmanagment.entities;

    import jakarta.persistence.*;
    import lombok.*;
    import org.hibernate.annotations.OnDelete;
    import org.hibernate.annotations.OnDeleteAction;

    import java.time.LocalDateTime;

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    @Entity
    @Table(name = "tickets")
    public class Ticket {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;

        @Column(nullable = false)
        private int quantity;

        @ManyToOne
        @JoinColumn(name = "event_id", nullable = false)
        @OnDelete(action = OnDeleteAction.CASCADE)
        private Event event;

        @ManyToOne
        @JoinColumn(name = "user_id", nullable = false)
        private User user;

        @Column(nullable = false)
        private String name;

        @Column(name = "start_date", nullable = false)
        private LocalDateTime startDate;

        @Column(name = "end_date", nullable = false)
        private LocalDateTime endDate;
    }