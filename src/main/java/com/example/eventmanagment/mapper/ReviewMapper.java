package com.example.eventmanagment.mapper;

import com.example.eventmanagment.dto.review.ReviewDto;
import com.example.eventmanagment.entities.Review;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ReviewMapper {
    @Mapping(source = "user",  target = "userId")
    @Mapping(source = "event", target = "eventId")
    ReviewDto toDto(Review review);

    /** DTO → Entity **/
    @Mapping(source = "userId",  target = "user")
    @Mapping(source = "eventId", target = "event")
    Review toEntity(ReviewDto dto);

    List<ReviewDto> toDtoList(List<Review> reviews);

    List<Review> toEntityList(List<ReviewDto> reviewDtos);
}
