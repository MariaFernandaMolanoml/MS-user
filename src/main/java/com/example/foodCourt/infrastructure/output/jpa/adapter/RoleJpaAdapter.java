package com.example.foodCourt.infrastructure.output.jpa.adapter;

import com.example.foodCourt.domain.exception.RoleNotFoundException;
import com.example.foodCourt.domain.model.Role;
import com.example.foodCourt.domain.spi.IRolePersistencePort;
import com.example.foodCourt.infrastructure.output.jpa.entity.RoleEntity;
import com.example.foodCourt.infrastructure.output.jpa.mapper.RoleEntityMapper;
import com.example.foodCourt.infrastructure.output.jpa.repository.IRoleRepository;
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
