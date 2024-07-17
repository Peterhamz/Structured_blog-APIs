package com.blog.structured_blog.service.impl;

import com.blog.structured_blog.entity.Category;
import com.blog.structured_blog.payload.CategoryDto;
import com.blog.structured_blog.repository.CategoryRepository;
import com.blog.structured_blog.service.CategoryService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class CategoryServiceImpl implements CategoryService {

    private CategoryRepository categoryRepository;
    private ModelMapper modelMapper;

    public CategoryServiceImpl(CategoryRepository categoryRepository, ModelMapper modelMapper) {
        this.categoryRepository = categoryRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public CategoryDto addCategory(CategoryDto categoryDto) {
      Category category = modelMapper.map(categoryDto, Category.class);
      Category savedCategory = categoryRepository.save(category);
        return modelMapper.map(savedCategory, CategoryDto.class);
    }
}
