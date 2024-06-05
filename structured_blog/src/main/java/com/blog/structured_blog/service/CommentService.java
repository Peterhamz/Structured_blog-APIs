package com.blog.structured_blog.service;

import com.blog.structured_blog.payload.CommentDto;

public interface CommentService {
    CommentDto createComment(long postId, CommentDto commentDto);
}
