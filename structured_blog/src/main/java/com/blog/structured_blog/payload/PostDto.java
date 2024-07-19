package com.blog.structured_blog.payload;
import com.blog.structured_blog.entity.Comment;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.util.Set;

@Data
public class PostDto {


    private Long id;

    @NotEmpty
    @Size(min = 2, message = "Post title should have at least two characters")
    private String title;

    @NotEmpty
    @Size(min = 10, message = "Post description should have at least ten characters")
    private String description;

    @NotEmpty
    private String content;
    private Set<Comment> comments;

    private Long categoryId;
}

