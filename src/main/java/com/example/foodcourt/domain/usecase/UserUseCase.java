package com.example.foodcourt.domain.usecase;

import com.example.foodcourt.domain.api.IUserServicePort;
import com.example.foodcourt.domain.constants.Constants;
import com.example.foodcourt.domain.exception.AgeNotValidException;
import com.example.foodcourt.domain.exception.DocumentAlreadyExistsException;
import com.example.foodcourt.domain.exception.EmailAlreadyExistsException;
import com.example.foodcourt.domain.exception.RoleNotFoundException;
import com.example.foodcourt.domain.exception.UserNotFoundException;
import com.example.foodcourt.domain.model.Role;
import com.example.foodcourt.domain.model.User;
import com.example.foodcourt.domain.spi.IEncryptPasswordPersistencePort;
import com.example.foodcourt.domain.spi.IRolePersistencePort;
import com.example.foodcourt.domain.spi.IUserPersistencePort;

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
    public User getUserByDocument(String document) {
        User user = userPersistencePort.findByDocument(document);
        if (user == null) {
            throw new UserNotFoundException();
        }
        return user;
    }

    private void validateEmailNotExists(String email) {
        if (email == null || email.isBlank()) {
            throw new EmailAlreadyExistsException();
        }
        if (userPersistencePort.existsByEmail(email)) {
            throw new EmailAlreadyExistsException();
        }
    }

    private void validateDocumentNotExists(String document) {
        if (document == null || document.isBlank()) {
            throw new DocumentAlreadyExistsException();
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

    @Override
    public void saveEmployee(User user) {
        validateEmailNotExists(user.getEmail());
        validateDocumentNotExists(user.getDocument());

        Role role = rolePersistencePort.findByName(Constants.ROLE_EMPLOYEE);
        if (role == null) {
            throw new RoleNotFoundException();
        }

        user.setRole(role);
        user.setPassword(encryptPasswordPersistencePort.encryptPassword(user.getPassword()));

        userPersistencePort.saveUser(user);
    }
    @Override
    public void saveCustomer(User user) {
        validateEmailNotExists(user.getEmail());
        validateDocumentNotExists(user.getDocument());

        Role role = rolePersistencePort.findByName(Constants.ROLE_CUSTOMER);
        if (role == null) {
            throw new RoleNotFoundException();
        }

        user.setRole(role);
        user.setPassword(encryptPasswordPersistencePort.encryptPassword(user.getPassword()));

        userPersistencePort.saveUser(user);
    }
}
