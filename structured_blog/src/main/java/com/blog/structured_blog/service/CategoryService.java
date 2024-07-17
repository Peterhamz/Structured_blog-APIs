package com.blog.structured_blog.service;

import com.blog.structured_blog.entity.Category;
import com.blog.structured_blog.payload.CategoryDto;

public interface CategoryService {
    CategoryDto addCategory(CategoryDto category);
}
