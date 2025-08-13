package com.example.foodCourt.infrastructure.security;

import com.example.foodCourt.domain.spi.IEncryptPasswordPersistencePort;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class EncryptPasswordAdapter implements IEncryptPasswordPersistencePort {

    private final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @Override
    public String encryptPassword(String password) {
        return passwordEncoder.encode(password);
    }
}
