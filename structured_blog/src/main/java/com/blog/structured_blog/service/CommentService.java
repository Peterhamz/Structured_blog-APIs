package com.blog.structured_blog.service;

import com.blog.structured_blog.entity.Comment;
import com.blog.structured_blog.payload.CommentDto;

import java.util.List;
import java.util.logging.XMLFormatter;

public interface CommentService {
    CommentDto createComment(long postId, CommentDto commentDto);
    List<CommentDto> getCommentByPostId(long postId);
    CommentDto getCommentById(long postId, long commentId);
    CommentDto updateComment(long postId, long commentId, CommentDto commentDto);
    void deleteComment(Long postId, Long commentId);
}
