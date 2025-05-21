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
public class EventDto {
    private Long id;
    @Size(min = 2, max = 50,message = "Name must be between 2 and 50 characters")
    @NotNull

    private String name;
    @Size(min = 2, max = 1000,message = "Description must be between 2 and 1000 characters")
    @NotNull
    private String description;
    @NotNull

    private UserDto userId;
    @NotBlank
    @NotNull
    private LocalDateTime startDate;

    @NotNull
    private LocalDateTime endDate;
    @NotNull
    private String imageUrl;

    @NotNull
    private EventType eventType;
    @NotNull
    private CategoryDto categoryId;

    @NotNull
    private VenueDto venueId;

    @Size(min = 2, max = 50,message = "Created by must be between 2 and 50 characters")
    @NotNull
    private String createdBy;
    private LocalDateTime createdAt;

}
