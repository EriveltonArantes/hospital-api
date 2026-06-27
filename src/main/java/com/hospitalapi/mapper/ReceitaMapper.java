package com.hospitalapi.mapper;

import com.hospitalapi.dto.ReceitaRequestDTO;
import com.hospitalapi.dto.ReceitaResponseDTO;
import com.hospitalapi.model.Receita;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ReceitaMapper {

    @Mapping(target = "paciente", ignore = true)
    @Mapping(target = "medico", ignore = true)
    Receita toEntity(ReceitaRequestDTO dto);

    @Mapping(target = "pacienteId", source = "paciente.id")
    @Mapping(target = "medicoId", source = "medico.id")
    ReceitaResponseDTO toResponseDTO(Receita entity);
}
