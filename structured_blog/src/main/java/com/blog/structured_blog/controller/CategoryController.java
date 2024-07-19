package com.blog.structured_blog.controller;

import com.blog.structured_blog.payload.CategoryDto;
import com.blog.structured_blog.payload.CommentDto;
import com.blog.structured_blog.service.CategoryService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {

    private CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public ResponseEntity<CategoryDto> addCategory(@RequestBody CategoryDto categoryDto){
        return new ResponseEntity<>(categoryService.addCategory(categoryDto), HttpStatus.CREATED);
    }
    @GetMapping("/{categoryId}")
    public ResponseEntity<CategoryDto> getCategory(@PathVariable(name = "categoryId") Long categoryId){
        return new ResponseEntity<>(categoryService.getCategory(categoryId), HttpStatus.OK);
    }
    @GetMapping()
    public ResponseEntity<List<CategoryDto>> getAllCategory(){
        return new ResponseEntity<>(categoryService.getAllCategory(),HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("{categoryId}")
    public ResponseEntity<CategoryDto> updateCategory(@RequestBody CategoryDto categoryDto,
                                                      @PathVariable(name = "categoryId")Long categoryId){
        return ResponseEntity.ok(categoryService.updateCategory(categoryDto,categoryId));
    }
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("{categoryId}")
    public ResponseEntity<String> deleteCategory(@PathVariable("categoryId") Long categoryId){
        categoryService.deleteCategory(categoryId);
        return ResponseEntity.ok("Category Deleted Successfully");
    }
}
