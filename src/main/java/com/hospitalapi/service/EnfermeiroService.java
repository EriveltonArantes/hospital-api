package com.hospitalapi.service;

import com.hospitalapi.dto.EnfermeiroRequestDTO;
import com.hospitalapi.dto.EnfermeiroResponseDTO;
import com.hospitalapi.exception.ResourceNotFoundException;
import com.hospitalapi.mapper.EnfermeiroMapper;
import com.hospitalapi.model.Enfermeiro;
import com.hospitalapi.repository.EnfermeiroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class EnfermeiroService {

    @Autowired
    private EnfermeiroRepository repository;

    @Autowired
    private EnfermeiroMapper mapper;

    public List<EnfermeiroResponseDTO> listar() {
        return repository.findAll().stream().map(mapper::toResponseDTO).collect(Collectors.toList());
    }

    public EnfermeiroResponseDTO buscar(Long id) {
        Enfermeiro entity = repository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Enfermeiro não encontrado com id: " + id));
        return mapper.toResponseDTO(entity);
    }

    public EnfermeiroResponseDTO criar(EnfermeiroRequestDTO dto) {
        Enfermeiro entity = mapper.toEntity(dto);
        Enfermeiro salvo = repository.save(entity);
        return mapper.toResponseDTO(salvo);
    }

    public EnfermeiroResponseDTO atualizar(Long id, EnfermeiroRequestDTO dto) {
        if (!repository.existsById(id)) {
            throw new ResourceNotFoundException("Enfermeiro não encontrado com id: " + id);
        }
        Enfermeiro entity = mapper.toEntity(dto);
        entity.setId(id);
        Enfermeiro salvo = repository.save(entity);
        return mapper.toResponseDTO(salvo);
    }

    public void deletar(Long id) {
        if (!repository.existsById(id)) {
            throw new ResourceNotFoundException("Enfermeiro não encontrado com id: " + id);
        }
        repository.deleteById(id);
    }
}
