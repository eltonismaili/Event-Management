package com.example.eventmanagment.entities;

import com.example.eventmanagment.entities.enums.Role;
import jakarta.persistence.*;
import lombok.*;
import org.antlr.v4.runtime.misc.NotNull;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "users")
@Builder
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "name", nullable = false)
    @NotNull
    private String name;
    @Column(name = "surname", nullable = false)
    @NotNull
    private String surname;
    @Column(name = "email", nullable = false, unique = true)
    @NotNull
    private String email;
    @Column(name = "password", nullable = false)
    @NotNull
    private String password;
    @Column(name = "age", nullable = false)
    @NotNull
    private int age;
    @Enumerated(EnumType.STRING)
    private Role roles;
    @ManyToOne
    @JoinColumn(name = "address_id", nullable = false)
    private Address address;
    @Column(name = "created_at")
    private LocalDateTime createdAt;
    @Column(name = "created_by")
    private String createdBy;
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;
    @Column(name = "updated_by")
    private String updatedBy;
}
