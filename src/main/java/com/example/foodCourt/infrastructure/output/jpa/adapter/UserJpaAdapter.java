package com.example.foodCourt.infrastructure.output.jpa.adapter;

import com.example.foodCourt.domain.exception.*;
import com.example.foodCourt.domain.model.User;
import com.example.foodCourt.domain.spi.IUserPersistencePort;
import com.example.foodCourt.infrastructure.output.jpa.entity.UserEntity;
import com.example.foodCourt.infrastructure.output.jpa.mapper.UserEntityMapper;
import com.example.foodCourt.infrastructure.output.jpa.repository.IUserRepository;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class UserJpaAdapter implements IUserPersistencePort {

    private final IUserRepository userRepository;
    private final UserEntityMapper userEntityMapper;

    @Override
    public User saveUser(User user) {
        UserEntity entity = userEntityMapper.toEntity(user);
        return userEntityMapper.toUser(userRepository.save(entity));
    }

    @Override
    public Boolean existsByDocument(String document) {
        return userRepository.existsByDocument(document);
    }

    @Override
    public Boolean existsByEmail(String email) {
        return userRepository.existsByEmail(email);
    }

    @Override
    public User findByDocument(String document) {
        return userRepository.findByDocument(document)
                .map(userEntityMapper::toUser)
                .orElseThrow(UserNotFoundException::new);
    }
    @Override
    public User findById(Long id) {
        return userEntityMapper.toUser(userRepository.findById(id).orElse(null));
    }
}