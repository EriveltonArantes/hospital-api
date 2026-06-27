package com.hospitalapi.mapper;

import com.hospitalapi.dto.InternacaoRequestDTO;
import com.hospitalapi.dto.InternacaoResponseDTO;
import com.hospitalapi.model.Internacao;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface InternacaoMapper {

    @Mapping(target = "paciente", ignore = true)
    @Mapping(target = "medico", ignore = true)
    @Mapping(target = "leito", ignore = true)
    Internacao toEntity(InternacaoRequestDTO dto);

    @Mapping(target = "pacienteId", source = "paciente.id")
    @Mapping(target = "medicoId", source = "medico.id")
    @Mapping(target = "leitoId", source = "leito.id")
    InternacaoResponseDTO toResponseDTO(Internacao entity);
}
