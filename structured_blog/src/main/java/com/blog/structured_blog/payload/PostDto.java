package com.blog.structured_blog.payload;
import com.blog.structured_blog.entity.Comment;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.util.Set;

@Data
@Schema(
        description = "PostDto Model Information"
)
public class PostDto {


    private Long id;

    @Schema(
            description = "Post title Information"
    )
    @NotEmpty
    @Size(min = 2, message = "Post title should have at least two characters")
    private String title;

    @Schema(
            description = "Post description Information"
    )
    @NotEmpty
    @Size(min = 10, message = "Post description should have at least ten characters")
    private String description;

    @Schema(
            description = "Post content Information"
    )
    @NotEmpty
    private String content;
    @Schema(
            description = "Post comments Information"
    )
    private Set<Comment> comments;

    @Schema(
            description = "Post Category Information"
    )
    private Long categoryId;
}

