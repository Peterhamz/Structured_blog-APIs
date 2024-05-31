package com.blog.structured_blog.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(
        name = "posts", uniqueConstraints = {@UniqueConstraint(columnNames = {"post_title"})}
)
public class Post {
    @Id
    @GeneratedValue(
            strategy = GenerationType.IDENTITY
    )
    private Long id;
    @Column(name = "post_title", nullable = false)
    private String title;
    @Column(name = "post_description", nullable = false)
    private String description;
    @Column(name = "post_content", nullable = false)
    private String content;
}
