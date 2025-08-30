package com.example.foodcourt.infrastructure.input.rest;


import com.example.foodcourt.application.dto.SaveDtoRequest;
import com.example.foodcourt.application.dto.UserResponse;
import com.example.foodcourt.application.handler.IUserHandler;
import com.example.foodcourt.infrastructure.exception.ApiResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("users")
@RequiredArgsConstructor
public class UserRestController {
    private final IUserHandler userHandler;

    @PostMapping("/save/owner")
    public ResponseEntity<ApiResponse> register(@Valid @RequestBody SaveDtoRequest saveRequest) {
        userHandler.saveOwner(saveRequest);

        ApiResponse response = new ApiResponse(
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

    @PostMapping("/save/employee")
    public ResponseEntity<ApiResponse> registerEmployee(@Valid @RequestBody SaveDtoRequest saveRequest) {
        userHandler.saveEmployee(saveRequest);

        ApiResponse response = new ApiResponse(
                java.time.LocalDateTime.now(),
                HttpStatus.CREATED.value(),
                HttpStatus.CREATED.getReasonPhrase(),
                "Employee created successfully"
        );

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
    @PostMapping("/save/customer")
    public ResponseEntity<Object> registerCustomer(@Valid @RequestBody SaveDtoRequest saveRequest) {
        userHandler.saveCustomer(saveRequest);

        ApiResponse response = new ApiResponse(
                java.time.LocalDateTime.now(),
                HttpStatus.CREATED.value(),
                HttpStatus.CREATED.getReasonPhrase(),
                "Client created successfully"
        );

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
}
