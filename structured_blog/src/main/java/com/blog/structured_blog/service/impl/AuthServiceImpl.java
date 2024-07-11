package com.blog.structured_blog.service.impl;

import com.blog.structured_blog.entity.Role;
import com.blog.structured_blog.entity.User;
import com.blog.structured_blog.exception.BlogApiException;
import com.blog.structured_blog.payload.LoginDto;
import com.blog.structured_blog.payload.RegisterDto;
import com.blog.structured_blog.repository.RoleRepository;
import com.blog.structured_blog.repository.UserRepository;
import com.blog.structured_blog.security.JwtTokenProvider;
import com.blog.structured_blog.service.AuthService;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class AuthServiceImpl implements AuthService {
    private AuthenticationManager authenticationManager;
    private UserRepository userRepository;
    private RoleRepository roleRepository;
    private PasswordEncoder passwordEncoder;
    private JwtTokenProvider jwtTokenProvider;

    public AuthServiceImpl(AuthenticationManager authenticationManager,
                           UserRepository userRepository,
                           RoleRepository roleRepository,
                           PasswordEncoder passwordEncoder,
                           JwtTokenProvider jwtTokenProvider) {
        this.authenticationManager = authenticationManager;
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtTokenProvider = jwtTokenProvider;
    }

    @Override
    public String login(LoginDto loginDto) {

       Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginDto.getUsernameOrEmail(),
                loginDto.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);

        String token = jwtTokenProvider.generateToken(authentication);
        return token;
    }

    @Override
    public String register(RegisterDto registerDto) {

        //Check if user already exist
        if (userRepository.existsByUsername(registerDto.getUsername())){
            throw  new BlogApiException(HttpStatus.BAD_REQUEST, "User Already Exist");
        }

        //Check if email already exist
       if (userRepository.existsByEmail(registerDto.getEmail())){
           throw  new BlogApiException(HttpStatus.BAD_REQUEST, "User with that email already exist");
       }

        User user = new User();
       user.setEmail(registerDto.getEmail());
       user.setUsername(registerDto.getUsername());
       user.setName(registerDto.getName());
       user.setPassword(passwordEncoder.encode(registerDto.getPassword()));

        Set<Role> roles = new HashSet<>();

        Role userRoles = roleRepository.findByName("ROLE_USER").get();
        roles.add(userRoles);
        user.setRoles(roles );
        userRepository.save(user);
        return "User Registered successfully!. ";
    }
}
