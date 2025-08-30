package com.example.foodcourt.application.handler;

import com.example.foodcourt.application.dto.SaveDtoRequest;
import com.example.foodcourt.application.dto.UserResponse;
import jakarta.validation.Valid;

public interface IUserHandler {
    void saveOwner(SaveDtoRequest saveDtoRequest);
    UserResponse getUserByDocument(String document);
    void saveEmployee(@Valid SaveDtoRequest saveRequest);
    void saveCustomer(@Valid SaveDtoRequest saveRequest);
}
