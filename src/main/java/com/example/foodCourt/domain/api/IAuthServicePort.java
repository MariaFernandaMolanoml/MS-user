package com.example.foodCourt.domain.api;

import com.example.foodCourt.domain.model.User;

public interface IAuthServicePort {
    User login(String email, String password);
}
