package com.ecommerce.service.impl;

import com.ecommerce.dto.request.CategoryRequestDto;
import com.ecommerce.dto.response.CategoryResponseDto;
import com.ecommerce.entity.Category;
import com.ecommerce.exception.CategoryNotFoundException;
import com.ecommerce.exception.DuplicateCategoryException;
import com.ecommerce.mapper.CategoryMapper;
import com.ecommerce.repository.CategoryRepository;
import com.ecommerce.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;
    private final CategoryMapper categoryMapper;

    @Override
    public CategoryResponseDto createCategory(CategoryRequestDto dto) {

        if (categoryRepository.existsByName(dto.getName())) {
            throw new DuplicateCategoryException("Category name cannot be same");
        }

        Category category = categoryMapper.mapToEntity(dto);
        Category savedCategory = categoryRepository.save(category);
        return categoryMapper.mapToDto(savedCategory);
    }


    @Override
    public List<CategoryResponseDto> getAllCategories() {

        List<Category> categories = categoryRepository.findAll();
        List<CategoryResponseDto> dtoList = new ArrayList<>();

        for (Category category : categories) {
            dtoList.add(categoryMapper.mapToDto(category));
        }
        return dtoList;
    }

    @Override
    public CategoryResponseDto getCategoryById(Long id) {

        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new CategoryNotFoundException("Category not found"));
        return categoryMapper.mapToDto(category);
    }

    @Override
    public CategoryResponseDto updateCategory(Long id, CategoryRequestDto dto) {
        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new CategoryNotFoundException("Category not found"));

        category.setName(dto.getName());
        category.setDescription(dto.getDescription());

        Category updatedCategory = categoryRepository.save(category);
        return categoryMapper.mapToDto(updatedCategory);
    }

    @Override
    public void deleteCategory(Long id) {

        categoryRepository.findById(id)
                .orElseThrow(() -> new CategoryNotFoundException("Category not found"));
        categoryRepository.deleteById(id);
    }

}
