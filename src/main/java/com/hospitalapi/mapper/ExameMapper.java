package com.hospitalapi.mapper;

import com.hospitalapi.dto.ExameRequestDTO;
import com.hospitalapi.dto.ExameResponseDTO;
import com.hospitalapi.model.Exame;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ExameMapper {

    @Mapping(target = "paciente", ignore = true)
    @Mapping(target = "medico", ignore = true)
    Exame toEntity(ExameRequestDTO dto);

    @Mapping(target = "pacienteId", source = "paciente.id")
    @Mapping(target = "medicoId", source = "medico.id")
    ExameResponseDTO toResponseDTO(Exame entity);
}
