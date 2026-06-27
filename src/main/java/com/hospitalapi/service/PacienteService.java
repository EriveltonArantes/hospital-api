package com.hospitalapi.service;

import com.hospitalapi.dto.PacienteRequestDTO;
import com.hospitalapi.dto.PacienteResponseDTO;
import com.hospitalapi.exception.ResourceNotFoundException;
import com.hospitalapi.mapper.PacienteMapper;
import com.hospitalapi.model.Paciente;
import com.hospitalapi.repository.PacienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class PacienteService {

    @Autowired
    private PacienteRepository repository;

    @Autowired
    private PacienteMapper mapper;

    @Transactional(readOnly = true)
    public List<PacienteResponseDTO> listar() {
        return repository.findAll().stream().map(mapper::toResponseDTO).collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public PacienteResponseDTO buscar(Long id) {
        Paciente entity = repository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Paciente não encontrado com id: " + id));
        return mapper.toResponseDTO(entity);
    }

    public PacienteResponseDTO criar(PacienteRequestDTO dto) {
        Paciente entity = mapper.toEntity(dto);
        Paciente salvo = repository.save(entity);
        return mapper.toResponseDTO(salvo);
    }

    public PacienteResponseDTO atualizar(Long id, PacienteRequestDTO dto) {
        if (!repository.existsById(id)) {
            throw new ResourceNotFoundException("Paciente não encontrado com id: " + id);
        }
        Paciente entity = mapper.toEntity(dto);
        entity.setId(id);
        Paciente salvo = repository.save(entity);
        return mapper.toResponseDTO(salvo);
    }

    public void deletar(Long id) {
        if (!repository.existsById(id)) {
            throw new ResourceNotFoundException("Paciente não encontrado com id: " + id);
        }
        repository.deleteById(id);
    }
}
