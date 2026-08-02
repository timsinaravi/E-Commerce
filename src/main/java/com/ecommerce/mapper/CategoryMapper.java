package com.ecommerce.mapper;

import com.ecommerce.dto.request.CategoryRequestDto;
import com.ecommerce.dto.response.CategoryResponseDto;
import com.ecommerce.entity.Category;
import org.springframework.stereotype.Component;

@Component
public class CategoryMapper {

    public Category mapToEntity(CategoryRequestDto dto) {

        Category category = new Category();
        category.setName(dto.getName());
        category.setDescription(dto.getDescription());

        return category;
    }

    public CategoryResponseDto mapToDto(Category category) {

        CategoryResponseDto dto = new CategoryResponseDto();
        dto.setId(category.getId());
        dto.setName(category.getName());
        dto.setDescription(category.getDescription());

        return dto;
    }



}
