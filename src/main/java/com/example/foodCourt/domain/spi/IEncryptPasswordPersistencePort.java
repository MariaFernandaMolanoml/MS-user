package com.example.foodCourt.domain.spi;

public interface IEncryptPasswordPersistencePort {
    String encryptPassword(String password);
}
