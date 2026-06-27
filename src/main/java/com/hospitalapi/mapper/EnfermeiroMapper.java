package com.hospitalapi.mapper;

import com.hospitalapi.dto.EnfermeiroRequestDTO;
import com.hospitalapi.dto.EnfermeiroResponseDTO;
import com.hospitalapi.model.Enfermeiro;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface EnfermeiroMapper {

    Enfermeiro toEntity(EnfermeiroRequestDTO dto);

    EnfermeiroResponseDTO toResponseDTO(Enfermeiro entity);
}
