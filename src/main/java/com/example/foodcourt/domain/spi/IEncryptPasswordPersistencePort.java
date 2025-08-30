package com.example.foodcourt.domain.spi;

public interface IEncryptPasswordPersistencePort {
    String encryptPassword(String password);
}
