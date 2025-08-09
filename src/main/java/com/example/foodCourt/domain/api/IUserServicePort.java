package com.example.foodCourt.domain.api;
import com.example.foodCourt.domain.model.User;

public interface IUserServicePort {
    User saveOwner(User user);
    User getUser (String document);
}
