package com.example.eventmanagment.mapper;

import com.example.eventmanagment.dto.category.CategoryDto;
import com.example.eventmanagment.entities.Category;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CategoryMapper {
    CategoryDto toDto(Category category);

    Category toEntity(CategoryDto categoryDto);

    List<CategoryDto> toDtoList(List<Category> categories);

    List<Category> toEntityList(List<CategoryDto> categoryDtos);
}
