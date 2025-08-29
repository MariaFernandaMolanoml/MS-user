package com.example.foodCourt.domain.usecase;

import com.example.foodCourt.domain.api.IAuthServicePort;
import com.example.foodCourt.domain.model.User;
import com.example.foodCourt.domain.spi.IUserPersistencePort;
import org.springframework.security.crypto.password.PasswordEncoder;

public class AuthUseCase implements IAuthServicePort {

    private final IUserPersistencePort userPersistencePort;
    private final PasswordEncoder passwordEncoder;

    public AuthUseCase(IUserPersistencePort userPersistencePort, PasswordEncoder passwordEncoder) {
        this.userPersistencePort = userPersistencePort;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public User login(String email, String password) {
        User user = userPersistencePort.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Usuario o contraseña incorrectos"));

        if (!user.passwordMatches(password, passwordEncoder)) {
            throw new RuntimeException("Usuario o contraseña incorrectos");
        }

        return user;
    }
}
