package com.example.foodCourt.application.dto;

import lombok.Getter;
import lombok.Setter;
import java.time.LocalDate;

@Getter
@Setter

public class SaveDtoRequest {
    private String name;
    private String lastName;
    private String password;
    private String email;
    private String document;
    private String phone;
    private LocalDate birthDate;
}
