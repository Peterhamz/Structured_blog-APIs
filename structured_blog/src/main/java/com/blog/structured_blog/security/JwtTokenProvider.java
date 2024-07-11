package com.blog.structured_blog.security;

import com.blog.structured_blog.exception.BlogApiException;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.UnsupportedJwtException;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.security.Key;
import java.util.Base64;
import java.util.Date;

@Component
public class JwtTokenProvider {

    @Value("${app.jwt-secret}")
    private String jwtSecret;
    @Value("${app-jwt-expiration-milliseconds}")
    private long jwtExpirationDate;

    //Generate JWT token
    public String generateToken(Authentication authentication){

        String username = authentication.getName();
        Date  currentDate = new Date();
        Date expirationDate = new Date(currentDate.getTime() + jwtExpirationDate);

        String token = Jwts.builder()
                .subject(username)
                .issuedAt(new Date())
                .expiration(expirationDate)
                .signWith(key())
                .compact();

        return token;
    }

    //Convert secret key from sha to base64
    private Key key(){
       return Keys.hmacShaKeyFor(Decoders.BASE64.decode(jwtSecret));
    }


    // Get username from jwtToken
    public String getUsername(String token){
        return Jwts.parser()
                .verifyWith((SecretKey) key())
                .build()
                .parseSignedClaims( token)
                .getPayload()
                .getSubject();
    }

    //Validate JWT token
    public boolean validateToken(String token){
       try{
           Jwts.parser()
                   .verifyWith((SecretKey) key())
                   .build()
                   .parse(token);
           return true;
       } catch (ExpiredJwtException e) {
           throw new BlogApiException(HttpStatus.BAD_REQUEST, "Expired JWT token");
       } catch (MalformedJwtException e) {
           throw new BlogApiException(HttpStatus.BAD_REQUEST, "Invalid JWT token");
       } catch (UnsupportedJwtException e) {
           throw new BlogApiException(HttpStatus.BAD_REQUEST, "Unsupported JWT token");
       } catch (IllegalArgumentException e) {
           throw new BlogApiException(HttpStatus.BAD_REQUEST, "JWT claims is  null or empty");
       }
    }
}
