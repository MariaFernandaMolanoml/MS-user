package com.example.foodcourt.application.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;
import java.time.LocalDate;

@Getter
@Setter

public class SaveDtoRequest {

    @NotBlank(message = "First name is required")
    private String name;

    @NotBlank(message = "Last name is required")
    private String lastName;

    @NotBlank(message = "Password is required")
    private String password;

    @NotBlank(message = "Email is required")
    @Email(message = "Invalid email format")
    private String email;

    @NotBlank(message = "Document is required")
    @Pattern(regexp = "\\d+", message = "Document must contain only numbers")
    private String document;

    @NotBlank(message = "Phone number is required")
    @Pattern(regexp = "^\\+?\\d{1,13}$", message = "Phone number must be up to 13 digits and may start with +")
    private String phone;

    @NotNull(message = "Birth date is required")
    private LocalDate birthDate;
}