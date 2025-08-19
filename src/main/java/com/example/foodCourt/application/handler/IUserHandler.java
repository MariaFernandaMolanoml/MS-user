package com.example.foodCourt.application.handler;

import com.example.foodCourt.application.dto.SaveDtoRequest;
import com.example.foodCourt.application.dto.UserResponse;

public interface IUserHandler {
    void saveOwner(SaveDtoRequest saveDtoRequest);
    UserResponse getUserByDocument(String document);
}
