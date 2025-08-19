package com.example.foodCourt.application.mapper;

import com.example.foodCourt.application.dto.UserResponse;
import com.example.foodCourt.domain.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface UserResponseMapper {

    @Mapping(source = "role.name", target = "role")
    UserResponse toResponse(User user);
}
