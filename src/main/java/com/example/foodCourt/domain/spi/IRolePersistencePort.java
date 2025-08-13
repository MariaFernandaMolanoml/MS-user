package com.example.foodCourt.domain.spi;

import com.example.foodCourt.domain.model.Role;

public interface IRolePersistencePort {
    Boolean existsById(Long roleId);

    Role findByName(String name);
}
