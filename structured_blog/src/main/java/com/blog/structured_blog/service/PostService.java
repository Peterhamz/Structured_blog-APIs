package com.blog.structured_blog.service;

import com.blog.structured_blog.payload.PostDto;
import com.blog.structured_blog.payload.PostResponse;

import java.util.List;

public interface PostService {
    PostDto createPost(PostDto postDto);
    PostResponse getAllPost(int pageNo, int pageSize, String sortBy, String sortDir);

    PostDto getPostById(Long id);

    PostDto updatePost(PostDto postDto, Long id);

    void deletePostById(long id);
    List<PostDto> getPostByCategoryId(Long categoryId);


}
