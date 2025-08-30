package com.example.foodcourt.infrastructure.input.rest;

import com.example.foodcourt.application.dto.AuthenticationDtoResponse;
import com.example.foodcourt.application.dto.LoginDto;
import com.example.foodcourt.application.handler.AuthHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
