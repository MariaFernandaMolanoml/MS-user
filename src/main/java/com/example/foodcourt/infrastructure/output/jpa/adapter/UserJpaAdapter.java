package com.example.foodcourt.infrastructure.output.jpa.adapter;

import com.example.foodcourt.domain.exception.UserNotFoundException;
import com.example.foodcourt.domain.model.User;
import com.example.foodcourt.domain.spi.IUserPersistencePort;
import com.example.foodcourt.infrastructure.output.jpa.entity.UserEntity;
import com.example.foodcourt.infrastructure.output.jpa.mapper.UserEntityMapper;
import com.example.foodcourt.infrastructure.output.jpa.repository.IUserRepository;
import lombok.RequiredArgsConstructor;

import java.util.Optional;

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
    public Optional<User> findByEmail(String email) {
        return userRepository.findByEmail(email)
                .map(userEntityMapper::toUser);
    }
}