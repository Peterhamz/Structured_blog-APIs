package com.blog.structured_blog.controller;

import com.blog.structured_blog.payload.CommentDto;
import com.blog.structured_blog.service.CommentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/")
public class CommentController {

    private CommentService commentService;

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    public ResponseEntity<CommentDto> createComment(long postId, CommentDto commentDto){

        return null;
    }
}
