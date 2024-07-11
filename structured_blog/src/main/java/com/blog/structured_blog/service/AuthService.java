package com.blog.structured_blog.service;

import com.blog.structured_blog.payload.LoginDto;
import com.blog.structured_blog.payload.RegisterDto;

public interface AuthService {
    String login(LoginDto loginDto);
    String register(RegisterDto registerDto);
}
