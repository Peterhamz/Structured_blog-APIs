package com.blog.structured_blog.controller;

import com.blog.structured_blog.payload.JWTAuthResponse;
import com.blog.structured_blog.payload.LoginDto;
import com.blog.structured_blog.payload.RegisterDto;
import com.blog.structured_blog.service.AuthService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
@Tag(
        name = "REST API for Authentication resources"
)
public class AuthController { 
    private AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    // endpoint for login
    @PostMapping(value = {"/login","signin"})
    public ResponseEntity<JWTAuthResponse> login(@RequestBody LoginDto loginDto){
        String token =  authService.login(loginDto);

        JWTAuthResponse jwtAuthResponse = new JWTAuthResponse();
        jwtAuthResponse.setAccessToken(token);
        return  ResponseEntity.ok(jwtAuthResponse);
    }

    // endpoint for signup
    @PostMapping(value = {"/register","signup"})
    public ResponseEntity<String> register(@RequestBody RegisterDto registerDto){
        String response =  authService.register(registerDto);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }
}
