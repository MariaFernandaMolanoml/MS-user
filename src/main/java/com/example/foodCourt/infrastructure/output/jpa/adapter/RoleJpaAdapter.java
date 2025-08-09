package com.example.foodCourt.infrastructure.output.jpa.adapter;

import com.example.foodCourt.domain.api.IUserServicePort;
import com.example.foodCourt.domain.model.User;
import com.example.foodCourt.domain.spi.IUserPersistencePort;
import com.example.foodCourt.infrastructure.output.jpa.mapper.UserEntityMapper;
import com.example.foodCourt.infrastructure.output.jpa.repository.IUserRepository;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class RoleJpaAdapter implements IUserPersistencePort {
    private final IUserRepository userRepository;
    private final UserEntityMapper userEntityMapper;

    @Override
    public User saveUser(User user) {
        return null;
    }

    @Override
    public Boolean existsByDocument(String document) {
        return null;
    }

    @Override
    public Boolean existsByEmail(String email) {
        return null;
    }

    @Override
    public User findByDocument(String document) {
        return null;
    }
}
