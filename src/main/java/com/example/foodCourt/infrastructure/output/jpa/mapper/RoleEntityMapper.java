package com.example.foodCourt.infrastructure.output.jpa.mapper;

import com.example.foodCourt.domain.model.Role;
import com.example.foodCourt.infrastructure.output.jpa.entity.RoleEntity;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)

public interface RoleEntityMapper {
    Role toRole(RoleEntity roleEntity);
}
