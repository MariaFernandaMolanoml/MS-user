package com.example.foodcourt.application.mapper;
import com.example.foodcourt.application.dto.SaveDtoRequest;
import com.example.foodcourt.domain.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel ="spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface SaveDtoRequestMapper {

    User toEntity(SaveDtoRequest saveDtoRequest);
}
