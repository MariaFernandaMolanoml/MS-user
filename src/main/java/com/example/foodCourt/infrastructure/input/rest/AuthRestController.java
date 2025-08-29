package com.example.foodCourt.infrastructure.input.rest;

import com.example.foodCourt.application.dto.AuthenticationDtoResponse;
import com.example.foodCourt.application.dto.LoginDto;
import com.example.foodCourt.application.handler.AuthHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthRestController {

    private final AuthHandler authHandler;

    @PostMapping("/login")
    public ResponseEntity<AuthenticationDtoResponse> login(@RequestBody LoginDto loginDto) {
        AuthenticationDtoResponse response = authHandler.login(loginDto);
        return ResponseEntity.ok(response);
    }
}
