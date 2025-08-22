package com.example.foodCourt.application.handler;

import com.example.foodCourt.application.dto.LoginDto;
import com.example.foodCourt.application.dto.AuthenticationDtoResponse;
import com.example.foodCourt.domain.api.IAuthServicePort;
import com.example.foodCourt.domain.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthHandler {

    private final IAuthServicePort authServicePort;
    private final com.example.foodCourt.application.security.JwtUtil jwtUtil;

    public AuthenticationDtoResponse login(LoginDto request) {
        User user = authServicePort.login(request.getEmail(), request.getPassword());
        String token = jwtUtil.generateToken(user.getEmail(), user.getRole().getName());
        return new AuthenticationDtoResponse(token);
    }
}