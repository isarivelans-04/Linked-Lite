package com.linkedlite.backend.security;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;

@Component
public class JwtUtil {

    private final SecretKey secretKey =
            Keys.hmacShaKeyFor("mySecretKeyForLinkedLiteJwtToken1234567890".getBytes());

    public String generateToken(String email) {

        return Jwts.builder()
                .subject(email)
                .signWith(secretKey)
                .compact();
    }
    public String extractEmail(String token) {
        return Jwts.parser()
                .verifyWith(secretKey)
                .build()
                .parseSignedClaims(token)
                .getPayload()
                .getSubject();
    }
    public boolean validateToken(String token, String email) {
        return extractEmail(token).equals(email);
    }
}
