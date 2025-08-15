package com.example.foodCourt.domain.spi;
import com.example.foodCourt.domain.model.User;


public interface IUserPersistencePort {
    User saveUser(User user);
    Boolean existsByDocument(String document);
    Boolean existsByEmail(String email);
    User findByDocument(String document);
    User findById(Long id);
}
