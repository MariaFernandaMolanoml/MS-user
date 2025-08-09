package com.example.foodCourt.infrastructure.input.rest;


import com.example.foodCourt.application.dto.SaveDtoRequest;
import com.example.foodCourt.application.handler.IUserHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserRestController {
    private final IUserHandler userHandler;

    @PostMapping("/save/owner")
    public ResponseEntity<Void> register(@RequestBody SaveDtoRequest saveRequest) {
        userHandler.saveOwner(saveRequest);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

}
