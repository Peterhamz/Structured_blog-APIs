package com.blog.structured_blog.payload;
import com.blog.structured_blog.entity.Comment;
import lombok.Data;

import java.util.Set;

@Data
public class PostDto {
    private Long id;
    private String title;
    private String description;
    private String content;
    private Set<Comment> comments;
}

