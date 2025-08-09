package com.example.foodCourt.application.handler;

import com.example.foodCourt.application.dto.SaveDtoRequest;
import com.example.foodCourt.application.mapper.SaveDtoRequestMapper;
import com.example.foodCourt.domain.api.IUserServicePort;
import com.example.foodCourt.domain.model.User;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Transactional
public class UserHandler implements IUserHandler{

    private final IUserServicePort userServicePort;
    private final SaveDtoRequestMapper saveDtoRequestMapper;

    @Override
    public User saveOwner(SaveDtoRequest saveDtoRequest) {
        User user = saveDtoRequestMapper.toEntity(saveDtoRequest);
        return userServicePort.saveOwner(user);
    }
}
