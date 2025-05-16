package com.example.eventmanagment.service;

import com.example.eventmanagment.dto.category.CategoryDto;

import java.util.List;

public interface CategoryService {
    List<CategoryDto> findAll();

    CategoryDto findById(Long id);

    CategoryDto create(CategoryDto categoryDto);

    CategoryDto update(Long id, CategoryDto categoryDto);

    void delete(Long id);
}
