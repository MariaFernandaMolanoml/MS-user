package com.example.foodCourt.domain.model;

import lombok.Data;
import java.time.LocalDate;

@Data
public class User {
    private Long id;
    private String name;
    private String lastName;
    private String password;
    private String email;
    private String document;
    private String phone;
    private LocalDate birthDate;
    private Role role;

}

