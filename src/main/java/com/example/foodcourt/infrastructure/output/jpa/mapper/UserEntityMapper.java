package com.example.foodcourt.infrastructure.output.jpa.mapper;

import com.example.foodcourt.domain.model.User;
import com.example.foodcourt.infrastructure.output.jpa.entity.UserEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;


@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE,
        uses = RoleEntityMapper.class)
public interface UserEntityMapper {

    @Mapping(source = "role", target = "roleEntity")
    UserEntity toEntity(User user);

    @Mapping(source = "roleEntity", target = "role")
    User toUser(UserEntity userEntity);
}
