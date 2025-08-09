package com.example.foodCourt.domain.spi;

public interface IEncryptPasswordPersistencePort {
    String encryptPassword(String password);

    String encrypt(String password);
}
