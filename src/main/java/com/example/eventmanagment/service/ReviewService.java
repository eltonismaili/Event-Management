package com.example.eventmanagment.service;

import com.example.eventmanagment.dto.review.ReviewDto;

import java.util.List;

public interface ReviewService {
    List<ReviewDto> findAll();

    ReviewDto findById(Long id);

    ReviewDto create(ReviewDto reviewDto);

    ReviewDto update(Long id, ReviewDto reviewDto);

    void delete(Long id);
}
