package com.example.foodcourt.application.handler;

import com.example.foodcourt.application.dto.SaveDtoRequest;
import com.example.foodcourt.application.dto.UserResponse;
import com.example.foodcourt.application.mapper.SaveDtoRequestMapper;
import com.example.foodcourt.application.mapper.UserResponseMapper;
import com.example.foodcourt.domain.api.IUserServicePort;
import com.example.foodcourt.domain.model.User;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Transactional
public class UserHandler implements IUserHandler{

    private final IUserServicePort userServicePort;
    private final SaveDtoRequestMapper saveDtoRequestMapper;
    private final UserResponseMapper userResponseMapper;

    @Override
    public void saveOwner(SaveDtoRequest saveDtoRequest) {
        User user = saveDtoRequestMapper.toEntity(saveDtoRequest);
        userServicePort.saveOwner(user);
    }
    @Override
    public UserResponse getUserByDocument(String document) {
        User user = userServicePort.getUserByDocument(document);
        return userResponseMapper.toResponse(user);
    }
    @Override
    public void saveEmployee(SaveDtoRequest saveDtoRequest) {
        User user = saveDtoRequestMapper.toEntity(saveDtoRequest);
        userServicePort.saveEmployee(user);
    }

    @Override
    public void saveCustomer(SaveDtoRequest saveRequest) {
        User user=saveDtoRequestMapper.toEntity(saveRequest);
        userServicePort.saveCustomer(user);
    }

}
