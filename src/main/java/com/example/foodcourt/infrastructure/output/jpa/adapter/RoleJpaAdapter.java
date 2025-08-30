package com.example.foodcourt.infrastructure.output.jpa.adapter;

import com.example.foodcourt.domain.exception.RoleNotFoundException;
import com.example.foodcourt.domain.model.Role;
import com.example.foodcourt.domain.spi.IRolePersistencePort;
import com.example.foodcourt.infrastructure.output.jpa.mapper.RoleEntityMapper;
import com.example.foodcourt.infrastructure.output.jpa.repository.IRoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class RoleJpaAdapter implements IRolePersistencePort {

    private final IRoleRepository roleRepository;
    private final RoleEntityMapper roleEntityMapper;

    @Override
    public Boolean existsById(Long roleId) {
        return roleRepository.existsById(roleId);
    }

    @Override
    public Role findByName(String name) {
        return roleRepository.findByNameIgnoreCase(name)
                .map(roleEntityMapper::toRole)
                .orElseThrow(RoleNotFoundException::new);
    }
}

