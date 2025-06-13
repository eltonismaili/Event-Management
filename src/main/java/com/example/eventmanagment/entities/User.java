package com.example.eventmanagment.entities;

import com.example.eventmanagment.entities.enums.Role;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
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

    @NotBlank(message = "Name is required.")
    @Size(max = 100, message = "Name must be less than 100 characters.")
    @Column(name = "name", nullable = false)
    private String name;

    @NotBlank(message = "Surname is required.")
    @Size(max = 100, message = "Surname must be less than 100 characters.")
    @Column(name = "surname", nullable = false)
    private String surname;

    @NotBlank(message = "Email is required.")
    @Email(message = "Email must be a valid format.")
    @Size(max = 150, message = "Email must be less than 150 characters.")
    @Column(name = "email", nullable = false, unique = true)
    private String email;

    @NotBlank(message = "Password is required.")
    @Size(min = 8, max = 100, message = "Password must be between 8 and 100 characters.")
    @Column(name = "password", nullable = false)
    private String password;

    @Min(value = 13, message = "Age must be at least 13.")
    @Max(value = 120, message = "Age must be realistic.")
    @Column(name = "age", nullable = false)
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
