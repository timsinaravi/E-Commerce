package com.ecommerce.service.impl;

import com.ecommerce.dto.request.ProductRequestDto;
import com.ecommerce.dto.response.ProductResponseDto;
import com.ecommerce.entity.Category;
import com.ecommerce.entity.Product;
import com.ecommerce.exception.CategoryNotFoundException;
import com.ecommerce.exception.ProductNotFoundException;
import com.ecommerce.mapper.CategoryMapper;
import com.ecommerce.mapper.ProductMapper;
import com.ecommerce.repository.CategoryRepository;
import com.ecommerce.repository.ProductRepository;
import com.ecommerce.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;
    private final ProductMapper productMapper;
    private final CategoryMapper categoryMapper;

    @Override
    public ProductResponseDto createProduct(ProductRequestDto dto) {

        Category category = categoryRepository.findById(dto.getCategoryId())
                .orElseThrow(()-> new CategoryNotFoundException("Category not found"));

        Product product = productMapper.mapToEntity(dto);
        product.setCategory(category);

        Product savedProduct = productRepository.save(product);
        return productMapper.mapToDto(savedProduct);
    }

    @Override
    public List<ProductResponseDto> getAllProducts() {

        List<Product> products = productRepository.findAll();
        List<ProductResponseDto> dtoList = new ArrayList<>();

        for (Product product : products) {
            dtoList.add(productMapper.mapToDto(product));
        }
        return dtoList;
    }

    @Override
    public ProductResponseDto getProductById(Long id) {

        Product product = productRepository.findById(id)
                .orElseThrow(()-> new ProductNotFoundException("Product not found"));

        return productMapper.mapToDto(product);
    }


    @Override
    public List<ProductResponseDto> searchProductByName(String name) {

        List<Product> products = productRepository.findByNameContainsIgnoreCase(name);

        List<ProductResponseDto> dtoList = new ArrayList<>();
        for (Product product : products) {
            dtoList.add(productMapper.mapToDto(product));
        }
        return dtoList;
    }

    @Override
    public List<ProductResponseDto> getProductsByCategory(Long categoryId) {

        Category category = categoryRepository.findById(categoryId)
                .orElseThrow(()-> new CategoryNotFoundException("Category not found"));

        List<Product> products = productRepository.findByCategory(category);
        List<ProductResponseDto> dtoList = new ArrayList<>();
        for (Product product : products){
             dtoList.add(productMapper.mapToDto(product));
        }
        return dtoList;
    }

    @Override
    public ProductResponseDto updateProduct(Long id, ProductRequestDto dto) {

        Product product = productRepository.findById(id)
                .orElseThrow(()-> new ProductNotFoundException("Product not found"));

        product.setName(dto.getName());
        product.setDescription(dto.getDescription());
        product.setPrice(dto.getPrice());
        product.setStock(dto.getStock());
        product.setImageUrl(dto.getImageUrl());

        Product updatedProduct = productRepository.save(product);
        return productMapper.mapToDto(updatedProduct);
    }

    @Override
    public void deleteProduct(Long id) {

        productRepository.findById(id)
                .orElseThrow(()-> new ProductNotFoundException("Product not found"));

        productRepository.deleteById(id);
    }

}
