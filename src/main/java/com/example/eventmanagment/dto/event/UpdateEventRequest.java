package com.example.eventmanagment.dto.event;

import com.example.eventmanagment.dto.category.CategoryDto;
import com.example.eventmanagment.dto.user.UserDto;
import com.example.eventmanagment.dto.venue.VenueDto;
import com.example.eventmanagment.entities.enums.EventType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateEventRequest {
    private Long id;
    @Size(min = 2, max = 50, message = "Name must be between 2 and 50 characters")
    @NotNull(message = "Name is required")
    @NotBlank
    private String name;
    @Size(min = 2, max = 1000, message = "Description must be between 2 and 1000 characters")
    @NotNull
    @NotBlank
    private String description;


    @NotNull
    private LocalDateTime startDate;

    @NotNull
    private LocalDateTime endDate;
    @NotBlank
    @NotNull
    private String imageUrl;
    private EventType eventType;

    @NotNull(message = "Category is required")
    private Long category;

    @NotNull(message = "Venue is required")
    private Long venue;

    private String updatedBy;

    private LocalDateTime updatedAt;

}
