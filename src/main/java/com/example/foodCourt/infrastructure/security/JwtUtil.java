package com.example.foodCourt.infrastructure.security;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.Date;

import static com.example.foodCourt.domain.constants.Constants.*;

@Component
public class JwtUtil {

    @Value("${jwt.secret}")
    private String SECRET_KEY;

    @Value("${jwt.expiration}")
    private long EXPIRATION_MS;

    public String generateToken(String email, String role, String document, LocalDate birthDate) {
        return Jwts.builder()
                .setSubject(email)
                .claim(ROLE, role)
                .claim(DOCUMENT, document)
                .claim(BIRTHDATE, birthDate.toString())
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_MS))
                .signWith(SignatureAlgorithm.HS256, SECRET_KEY)
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
        return Jwts.parser()
                .setSigningKey(SECRET_KEY)
                .parseClaimsJws(token)
                .getBody();
    }
}
