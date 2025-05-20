package com.example.eventmanagment.dto.review;

import com.example.eventmanagment.dto.event.EventDto;
import com.example.eventmanagment.dto.user.UserDto;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReviewDto {
    private Long id;
    @NotNull
    private UserDto userId;
    @NotNull
    private EventDto eventId;
    @Size(min = 2, max = 1000, message = "Comment must be between 2 and 1000 characters")
    @NotNull

    private String comment;
    @NotNull
    private int rating;
}
