package com.hospitalapi.mapper;

import com.hospitalapi.dto.MedicoRequestDTO;
import com.hospitalapi.dto.MedicoResponseDTO;
import com.hospitalapi.model.Medico;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface MedicoMapper {

    Medico toEntity(MedicoRequestDTO dto);

    MedicoResponseDTO toResponseDTO(Medico entity);
}
