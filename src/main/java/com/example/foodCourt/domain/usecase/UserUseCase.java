package com.example.foodCourt.domain.usecase;

import com.example.foodCourt.domain.api.IUserServicePort;
import com.example.foodCourt.domain.constants.Constants;
import com.example.foodCourt.domain.exception.*;
import com.example.foodCourt.domain.model.Role;
import com.example.foodCourt.domain.model.User;
import com.example.foodCourt.domain.spi.IEncryptPasswordPersistencePort;
import com.example.foodCourt.domain.spi.IRolePersistencePort;
import com.example.foodCourt.domain.spi.IUserPersistencePort;

import java.time.LocalDate;
import java.time.Period;

public class UserUseCase implements IUserServicePort {

    private final IUserPersistencePort userPersistencePort;
    private final IEncryptPasswordPersistencePort encryptPasswordPersistencePort;
    private final IRolePersistencePort rolePersistencePort;


    public UserUseCase(IUserPersistencePort userPersistencePort,
                       IEncryptPasswordPersistencePort encryptPasswordPersistencePort,
                       IRolePersistencePort rolePersistencePort) {
        this.userPersistencePort = userPersistencePort;
        this.encryptPasswordPersistencePort = encryptPasswordPersistencePort;
        this.rolePersistencePort = rolePersistencePort;
    }

    @Override
    public User saveOwner(User user) {
        validateEmailNotExists(user.getEmail());
        validateDocumentNotExists(user.getDocument());
        validateBirthDate(user.getBirthDate());

        Role role = rolePersistencePort.findByName(Constants.ROLE_OWNER);
        if (role == null) {
            throw new RoleNotFoundException();
        }

        user.setRole(role);
        user.setPassword(encryptPasswordPersistencePort.encryptPassword(user.getPassword()));

        return userPersistencePort.saveUser(user);
    }

    @Override
    public User getUser(String document) {
        User user = userPersistencePort.findByDocument(document);
        if (user == null) {
            throw new UserNotFoundException();
        }
        return user;
    }

    private void validateEmailNotExists(String email) {
        if (email == null || email.isBlank()) {
            throw new IllegalArgumentException("Email is mandatory");
        }
        if (userPersistencePort.existsByEmail(email)) {
            throw new EmailAlreadyExistsException();
        }
    }

    private void validateDocumentNotExists(String document) {
        if (document == null || document.isBlank()) {
            throw new IllegalArgumentException("Document is mandatory");
        }
        if (userPersistencePort.existsByDocument(document)) {
            throw new DocumentAlreadyExistsException();
        }
    }

    private void validateBirthDate(LocalDate birthDate) {
        if (birthDate == null) {
            throw new AgeNotValidException();
        }
        int age = Period.between(birthDate, LocalDate.now()).getYears();
        if (age < 18) {
            throw new AgeNotValidException();
        }
    }
}
