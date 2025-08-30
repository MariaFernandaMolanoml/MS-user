package com.example.foodcourt.domain.api;

import com.example.foodcourt.domain.model.User;

public interface IAuthServicePort {
    User login(String email, String password);
}
