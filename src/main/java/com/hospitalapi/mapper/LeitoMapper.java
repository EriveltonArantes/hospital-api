package com.hospitalapi.mapper;

import com.hospitalapi.dto.LeitoRequestDTO;
import com.hospitalapi.dto.LeitoResponseDTO;
import com.hospitalapi.model.Leito;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface LeitoMapper {

    Leito toEntity(LeitoRequestDTO dto);

    LeitoResponseDTO toResponseDTO(Leito entity);
}
