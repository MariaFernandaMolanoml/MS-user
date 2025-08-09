package com.example.foodCourt.domain.usecase;

import com.example.foodCourt.domain.api.IUserServicePort;
import com.example.foodCourt.domain.constants.Constants;
import com.example.foodCourt.domain.model.User;
import com.example.foodCourt.domain.spi.IEncryptPasswordPersistencePort;
import com.example.foodCourt.domain.spi.IRolePersistencePort;
import com.example.foodCourt.domain.spi.IUserPersistencePort;


public class UserUseCase implements IUserServicePort {

    private final IUserPersistencePort userPersistencePort;
    private final IEncryptPasswordPersistencePort encryptPasswordPersistencePort;
    private final IRolePersistencePort rolePersistencePort;

    public UserUseCase(IUserPersistencePort userPersistencePort, IEncryptPasswordPersistencePort encryptPasswordPersistencePort, IRolePersistencePort rolePersistencePort) {
        this.userPersistencePort = userPersistencePort;
        this.encryptPasswordPersistencePort = encryptPasswordPersistencePort;
        this.rolePersistencePort = rolePersistencePort;
    }


    @Override
    public User saveOwner(User user) {
        if (userPersistencePort.existsByEmail(user.getEmail())) {
            throw new IllegalArgumentException("The email is already registered");
        }

        if (userPersistencePort.existsByDocument(user.getDocument())) {
            throw new IllegalArgumentException("The document is already registered");
        }

        user.setRole(rolePersistencePort.findByName(Constants.ROLE_OWNER));

        user.setPassword(encryptPasswordPersistencePort.encrypt(user.getPassword()));

        return userPersistencePort.saveUser(user);
    }

    @Override
    public User getUser(String document) {
        User user = userPersistencePort.findByDocument(document);
        if (user == null) {
            throw new IllegalArgumentException("User not found");
        }
        return user;
    }
}