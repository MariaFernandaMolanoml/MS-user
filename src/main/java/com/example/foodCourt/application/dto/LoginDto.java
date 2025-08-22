package com.example.foodCourt.application.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;

@Getter
@Setter
@NoArgsConstructor
public class LoginDto {

    @Email(message = "Must be a valid email")
    @NotBlank(message = "The email cannot be empty")
    private String email;

    @NotBlank(message = "The password cannot be empty")
    private String password;

}
