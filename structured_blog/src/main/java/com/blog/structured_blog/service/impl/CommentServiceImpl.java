package com.blog.structured_blog.service.impl;

import com.blog.structured_blog.entity.Comment;
import com.blog.structured_blog.entity.Post;
import com.blog.structured_blog.exception.ResourceNotFoundException;
import com.blog.structured_blog.payload.CommentDto;
import com.blog.structured_blog.repository.CommentRepository;
import com.blog.structured_blog.repository.PostRepository;
import com.blog.structured_blog.service.CommentService;
import org.springframework.stereotype.Service;

@Service
public class CommentServiceImpl implements CommentService {

    private CommentRepository commentRepository;

    private PostRepository postRepository;

    public CommentServiceImpl(CommentRepository commentRepository, PostRepository postRepository) {
        this.commentRepository = commentRepository;
        this.postRepository = postRepository;
    }
    @Override
    public CommentDto createComment(long postId, CommentDto commentDto) {
        Comment comment = mapToEntity(commentDto);
        // find post by id
        Post post = postRepository.findById(postId)
                .orElseThrow(()-> new ResourceNotFoundException("Post", "id", postId));
        //set post to comment entity
        comment.setPost(post);
        // save to comment TBL
        Comment newComment = commentRepository.save(comment);
        return mapToDto(newComment);
    }

    private CommentDto mapToDto(Comment comment){
        CommentDto commentDto = new CommentDto();
        commentDto.setId(comment.getId());
        commentDto.setName(comment.getName());
        commentDto.setEmail(comment.getEmail());
        commentDto.setBody(comment.getBody());
        return commentDto;
    }

    private Comment mapToEntity(CommentDto commentDto){
        Comment comment = new Comment();
        comment.setId(commentDto.getId());
        comment.setName(commentDto.getName());
        comment.setEmail(commentDto.getEmail());
        comment.setBody(commentDto.getBody());
        return comment;
    }
}
