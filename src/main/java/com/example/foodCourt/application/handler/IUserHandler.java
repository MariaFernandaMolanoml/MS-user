package com.example.foodCourt.application.handler;

import com.example.foodCourt.application.dto.SaveDtoRequest;
import com.example.foodCourt.application.dto.UserResponse;
import jakarta.validation.Valid;

public interface IUserHandler {
    void saveOwner(SaveDtoRequest saveDtoRequest);
    UserResponse getUserByDocument(String document);
    void saveEmployee(@Valid SaveDtoRequest saveRequest);
    void saveCustomer(@Valid SaveDtoRequest saveRequest);
}
