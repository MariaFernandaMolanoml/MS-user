package com.example.foodcourt.domain.spi;

import com.example.foodcourt.domain.model.Role;

public interface IRolePersistencePort {
    Boolean existsById(Long roleId);

    Role findByName(String name);
}
