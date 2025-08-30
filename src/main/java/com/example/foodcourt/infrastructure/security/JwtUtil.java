package com.example.foodcourt.infrastructure.security;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.time.LocalDate;
import java.util.Date;

import static com.example.foodcourt.domain.constants.Constants.BIRTHDATE;
import static com.example.foodcourt.domain.constants.Constants.DOCUMENT;
import static com.example.foodcourt.domain.constants.Constants.ROLE;


@Component
public class JwtUtil {

    private final String secretKey;
    private final long expirationMs;

    public JwtUtil(@Value("${jwt.secret}") String secretKey,
                   @Value("${jwt.expiration}") long expirationMs) {
        this.secretKey = secretKey;
        this.expirationMs = expirationMs;
    }

    private Key getSigningKey() {
        return Keys.hmacShaKeyFor(secretKey.getBytes());
    }


    public String generateToken(String email, String role, String document, LocalDate birthDate) {
        return Jwts.builder()
                .setSubject(email)
                .claim(ROLE, role)
                .claim(DOCUMENT, document)
                .claim(BIRTHDATE, birthDate.toString())
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + expirationMs))
                .signWith(getSigningKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    public String getEmailFromToken(String token) {
        return getClaims(token).getSubject();
    }

    public String getRoleFromToken(String token) {
        return getClaims(token).get(ROLE, String.class);
    }

    public String getDocumentFromToken(String token) {
        return getClaims(token).get(DOCUMENT, String.class);
    }

    public String getBirthDateFromToken(String token) {
        return getClaims(token).get(BIRTHDATE, String.class);
    }

    public boolean isTokenValid(String token) {
        try {
            Claims claims = getClaims(token);
            return claims.getExpiration().after(new Date());
        } catch (Exception e) {
            return false;
        }
    }

    private Claims getClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(getSigningKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }
}
