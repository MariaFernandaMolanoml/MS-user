package com.example.foodcourt.application.handler;

import com.example.foodcourt.application.dto.LoginDto;
import com.example.foodcourt.application.dto.AuthenticationDtoResponse;
import com.example.foodcourt.domain.api.IAuthServicePort;
import com.example.foodcourt.domain.model.User;
import com.example.foodcourt.infrastructure.security.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthHandler {

    private final IAuthServicePort authServicePort;
    private final JwtUtil jwtUtil;

    public AuthenticationDtoResponse login(LoginDto request) {
        User user = authServicePort.login(request.getEmail(), request.getPassword());
        String token = jwtUtil.generateToken(user.getEmail(), user.getRole().getName(), user.getDocument(), user.getBirthDate());
        return new AuthenticationDtoResponse(token);
    }
}