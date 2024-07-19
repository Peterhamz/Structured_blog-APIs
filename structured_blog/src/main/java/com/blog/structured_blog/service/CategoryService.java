package com.blog.structured_blog.service;

import com.blog.structured_blog.entity.Post;
import com.blog.structured_blog.payload.CategoryDto;
import com.blog.structured_blog.payload.PostDto;

import java.util.List;

public interface CategoryService {
    CategoryDto addCategory(CategoryDto category);
    CategoryDto getCategory(Long categoryId);
    List<CategoryDto> getAllCategory();
    CategoryDto updateCategory(CategoryDto category, Long categoryId);
    void deleteCategory(Long categoryId);
}
