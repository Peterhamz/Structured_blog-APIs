package com.blog.structured_blog.controller;

import com.blog.structured_blog.payload.CommentDto;
import com.blog.structured_blog.service.CommentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/")
public class CommentController {

    private CommentService commentService;
    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }
    @PostMapping("/posts/{postId}/comments")
    public ResponseEntity<CommentDto> createComment(@PathVariable(value= "postId") long postId,
                                                   @RequestBody CommentDto commentDto){
    return new ResponseEntity<>(commentService.createComment(postId, commentDto), HttpStatus.CREATED);
    }
    @GetMapping("/posts/{postId}/comments")
    public List<CommentDto> getAllCommentByPostId(@PathVariable(name = "postId") Long postId){
        return commentService.getCommentByPostId(postId);
    }
    @GetMapping("/posts/{postId}/comments/{commentId}")
    public ResponseEntity<CommentDto> getCommentById(@PathVariable(name = "postId") Long postId, @PathVariable(name = "commentId")  Long commentId){
        return new ResponseEntity<>(commentService.getCommentById(postId, commentId),HttpStatus.OK);
    }
}
