package com.example.foodCourt.application.mapper;
import com.example.foodCourt.application.dto.SaveDtoRequest;
import com.example.foodCourt.domain.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel ="spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface SaveDtoRequestMapper {

    User toEntity(SaveDtoRequest saveDtoRequest);
}
