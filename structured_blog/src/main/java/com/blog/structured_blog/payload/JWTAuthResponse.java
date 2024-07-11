package com.blog.structured_blog.payload;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class JWTAuthResponse {
    private String accessToken;
    private String tokenType = "Bearer";
}
