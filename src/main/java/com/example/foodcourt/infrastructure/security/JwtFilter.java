package com.example.foodcourt.infrastructure.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Collections;

import static com.example.foodcourt.domain.constants.Constants.AUTHORIZATION;
import static com.example.foodcourt.domain.constants.Constants.BEARER;
import static com.example.foodcourt.domain.constants.Constants.BIRTHDATE;
import static com.example.foodcourt.domain.constants.Constants.DOCUMENT;
import static com.example.foodcourt.domain.constants.Constants.EMAIL;
import static com.example.foodcourt.domain.constants.Constants.INVALIDTOKEN;
import static com.example.foodcourt.domain.constants.Constants.ROLE;

@Component
@RequiredArgsConstructor
public class JwtFilter extends OncePerRequestFilter {

    private final JwtUtil jwtUtil;

    @Override
    protected void doFilterInternal(
            @NonNull HttpServletRequest request,
            @NonNull HttpServletResponse response,
            @NonNull FilterChain filterChain
    ) throws ServletException, IOException {

        String authHeader = request.getHeader(AUTHORIZATION);

        if (authHeader != null && authHeader.startsWith(BEARER)) {
            String token = authHeader.substring(7);

            if (jwtUtil.isTokenValid(token)) {
                String email = jwtUtil.getEmailFromToken(token);
                String role = jwtUtil.getRoleFromToken(token);

                request.setAttribute(EMAIL, email);
                request.setAttribute(ROLE, role);
                request.setAttribute(DOCUMENT, jwtUtil.getDocumentFromToken(token));
                request.setAttribute(BIRTHDATE, jwtUtil.getBirthDateFromToken(token));

                UsernamePasswordAuthenticationToken authentication =
                        new UsernamePasswordAuthenticationToken(
                                email,
                                null,
                                Collections.singletonList(new SimpleGrantedAuthority(role))
                        );

                SecurityContextHolder.getContext().setAuthentication(authentication);
            }
        } else if (authHeader != null) {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.getWriter().write(INVALIDTOKEN);
            return;
        }

        filterChain.doFilter(request, response);
    }
}