package com.example.foodcourt.domain.usecase;

import com.example.foodcourt.domain.api.IAuthServicePort;
import com.example.foodcourt.domain.exception.InvalidCredentialsException;
import com.example.foodcourt.domain.model.User;
import com.example.foodcourt.domain.spi.IUserPersistencePort;
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
                .orElseThrow(InvalidCredentialsException::new);

        if (!user.passwordMatches(password, passwordEncoder)) {
            throw new InvalidCredentialsException();
        }

        return user;
    }
}
