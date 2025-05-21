package com.example.eventmanagment.service.impls;

import com.example.eventmanagment.dto.category.CategoryDto;
import com.example.eventmanagment.exceptions.category.CategoryNotFoundException;
import com.example.eventmanagment.exceptions.registration.RegistrationNotFoundException;
import com.example.eventmanagment.mapper.CategoryMapper;
import com.example.eventmanagment.repository.CategoryRepository;
import com.example.eventmanagment.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository categoryRepository;
    private final CategoryMapper categoryMapper;

    @Override
    public List<CategoryDto> findAll() {
        var categories = categoryRepository.findAll();
        return categoryMapper.toDtoList(categories);
    }

    @Override
    public CategoryDto findById(Long id) {
        var category = categoryRepository.findById(id)
                .orElseThrow(() -> new CategoryNotFoundException(id));
        return categoryMapper.toDto(category);
    }

    @Override
    public CategoryDto create(CategoryDto categoryDto) {
        var category = categoryMapper.toEntity(categoryDto);
        var savedCategory = categoryRepository.save(category);
        return categoryMapper.toDto(savedCategory);
    }

    @Override
    public CategoryDto update(Long id, CategoryDto categoryDto) {
        if (!id.equals(categoryDto.getId())) {
            throw new RegistrationNotFoundException(id);
        }

        var existingCategory = categoryRepository.findById(id)
                .orElseThrow(() -> new CategoryNotFoundException(id));

        existingCategory.setName(categoryDto.getName());

        var updatedCategory = categoryRepository.save(existingCategory);
        return categoryMapper.toDto(updatedCategory);
    }

    @Override
    public void delete(Long id) {
        if (!categoryRepository.existsById(id)) {
            throw new CategoryNotFoundException(id);
        }
        categoryRepository.deleteById(id);
    }
}
