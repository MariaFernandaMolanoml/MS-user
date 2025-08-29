package com.example.foodCourt.infrastructure.input.rest;


import com.example.foodCourt.application.dto.SaveDtoRequest;
import com.example.foodCourt.application.dto.UserResponse;
import com.example.foodCourt.application.handler.IUserHandler;
import com.example.foodCourt.domain.model.User;
import com.example.foodCourt.infrastructure.exception.ApiErrorResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("users")
@RequiredArgsConstructor
public class UserRestController {
    private final IUserHandler userHandler;

    @PostMapping("/save/owner")
    public ResponseEntity<ApiErrorResponse> register(@Valid @RequestBody SaveDtoRequest saveRequest) {
        userHandler.saveOwner(saveRequest);

        ApiErrorResponse response = new ApiErrorResponse(
                java.time.LocalDateTime.now(),
                HttpStatus.CREATED.value(),
                HttpStatus.CREATED.getReasonPhrase(),
                "User created successfully"
        );

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("/{document}")
    public ResponseEntity<UserResponse> getUserByDocument(@PathVariable String document) {
        return ResponseEntity.ok(userHandler.getUserByDocument(document));
    }

}
