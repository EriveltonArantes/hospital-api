package com.hospitalapi.mapper;

import com.hospitalapi.dto.PacienteRequestDTO;
import com.hospitalapi.dto.PacienteResponseDTO;
import com.hospitalapi.model.Paciente;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface PacienteMapper {

    Paciente toEntity(PacienteRequestDTO dto);

    PacienteResponseDTO toResponseDTO(Paciente entity);
}
