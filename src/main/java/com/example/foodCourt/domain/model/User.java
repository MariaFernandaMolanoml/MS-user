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

    public User(Long id, String name, String lastName, String document, String email, String phone, String password, Role role) {
        this.id = id;
        this.name = name;
        this.lastName = lastName;
        this.document = document;
        this.email = email;
        this.phone = phone;
        this.password = password;
        this.role = role;
    }

    public User() {}

    public boolean passwordMatches(String rawPassword, org.springframework.security.crypto.password.PasswordEncoder encoder) {
        return encoder.matches(rawPassword, this.password);
    }
}
