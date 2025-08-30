package com.example.foodcourt.domain.api;
import com.example.foodcourt.domain.model.User;

public interface IUserServicePort {
    User saveOwner(User user);
    User getUserByDocument (String document);
    void saveEmployee(User user);
    void saveCustomer(User user);
}
