package com.hospitalapi.mapper;

import com.hospitalapi.dto.ConsultaRequestDTO;
import com.hospitalapi.dto.ConsultaResponseDTO;
import com.hospitalapi.model.Consulta;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ConsultaMapper {

    @Mapping(target = "paciente", ignore = true)
    @Mapping(target = "medico", ignore = true)
    Consulta toEntity(ConsultaRequestDTO dto);

    @Mapping(target = "pacienteId", source = "paciente.id")
    @Mapping(target = "medicoId", source = "medico.id")
    ConsultaResponseDTO toResponseDTO(Consulta entity);
}
