package com.blog.structured_blog.payload;

import jakarta.persistence.Column;
import lombok.Data;

@Data
public class PostDto {
    private Long id;
    private String title;
    private String description;
    private String content;
}

