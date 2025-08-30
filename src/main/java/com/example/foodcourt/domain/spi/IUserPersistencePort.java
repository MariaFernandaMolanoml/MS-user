package com.example.foodcourt.domain.spi;

import com.example.foodcourt.domain.model.User;
import java.util.Optional;


public interface IUserPersistencePort {
    User saveUser(User user);
    Boolean existsByDocument(String document);
    Boolean existsByEmail(String email);
    User findByDocument(String document);
    Optional<User> findByEmail(String email);
}
