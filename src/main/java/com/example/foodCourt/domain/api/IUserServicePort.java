package com.example.foodCourt.domain.api;
import com.example.foodCourt.domain.model.User;

public interface IUserServicePort {
    User saveOwner(User user);
    User getUserByDocument (String document);
    User saveEmployee(User user);
    void saveCustomer(User user);
}
