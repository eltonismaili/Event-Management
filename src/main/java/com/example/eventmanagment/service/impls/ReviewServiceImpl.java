package com.example.eventmanagment.service.impls;

import com.example.eventmanagment.dto.review.ReviewDto;
import com.example.eventmanagment.entities.Event;
import com.example.eventmanagment.entities.Review;
import com.example.eventmanagment.entities.User;
import com.example.eventmanagment.exceptions.event.EventNotFoundException;
import com.example.eventmanagment.exceptions.review.ReviewNotFoundException;
import com.example.eventmanagment.exceptions.user.UserNotFoundException;
import com.example.eventmanagment.mapper.ReviewMapper;
import com.example.eventmanagment.repository.EventRepository;
import com.example.eventmanagment.repository.ReviewRepository;
import com.example.eventmanagment.repository.UserRepository;
import com.example.eventmanagment.service.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ReviewServiceImpl implements ReviewService {
    private final ReviewRepository reviewRepository;
    private final UserRepository userRepository;
    private final EventRepository eventRepository;
    private final ReviewMapper reviewMapper;

    @Override
    public List<ReviewDto> findAll() {
        var reviews = reviewRepository.findAll();
        return reviewMapper.toDtoList(reviews);
    }

    @Override
    public ReviewDto findById(Long id) {
        var review = reviewRepository.findById(id)
                .orElseThrow(() -> new ReviewNotFoundException(id));
        return reviewMapper.toDto(review);
    }

    @Override
    public ReviewDto create(ReviewDto dto) {
        // load User by nested DTO id
        User user = userRepository.findById(dto.getUserId().getId())
                .orElseThrow(() -> new UserNotFoundException(dto.getUserId().getId()));

        // load Event by nested DTO id
        Event event = eventRepository.findById(dto.getEventId().getId())
                .orElseThrow(() -> new EventNotFoundException(dto.getEventId().getId()));

        // map incoming DTO to entity, then overwrite user/event
        Review review = reviewMapper.toEntity(dto);
        review.setUser(user);
        review.setEvent(event);

        Review saved = reviewRepository.save(review);
        return reviewMapper.toDto(saved);
    }

    @Override
    public ReviewDto update(Long id, ReviewDto dto) {
        if (!reviewRepository.existsById(id)) {
            throw new RuntimeException("Review not found with id: " + id);
        }

        User user = userRepository.findById(dto.getUserId().getId())
                .orElseThrow(() -> new UserNotFoundException(dto.getUserId().getId()));

        Event event = eventRepository.findById(dto.getEventId().getId())
                .orElseThrow(() -> new EventNotFoundException(dto.getEventId().getId()));

        Review review = reviewMapper.toEntity(dto);
        review.setId(id);
        review.setUser(user);
        review.setEvent(event);

        Review updated = reviewRepository.save(review);
        return reviewMapper.toDto(updated);
    }

    @Override
    public void delete(Long id) {
        if (!reviewRepository.existsById(id)) {
            throw new ReviewNotFoundException(id);
        }
        reviewRepository.deleteById(id);
    }
}
