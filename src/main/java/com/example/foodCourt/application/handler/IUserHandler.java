package com.example.foodCourt.application.handler;

import com.example.foodCourt.application.dto.SaveDtoRequest;
import com.example.foodCourt.domain.model.User;

public interface IUserHandler {
     User saveOwner(SaveDtoRequest saveDtoRequest);

}
