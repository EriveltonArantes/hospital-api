package com.hospitalapi.service;

import com.hospitalapi.dto.MedicoRequestDTO;
import com.hospitalapi.dto.MedicoResponseDTO;
import com.hospitalapi.exception.ResourceNotFoundException;
import com.hospitalapi.mapper.MedicoMapper;
import com.hospitalapi.model.Medico;
import com.hospitalapi.repository.MedicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class MedicoService {

    @Autowired
    private MedicoRepository repository;

    @Autowired
    private MedicoMapper mapper;

    @Transactional(readOnly = true)
    public List<MedicoResponseDTO> listar() {
        return repository.findAll().stream().map(mapper::toResponseDTO).collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public MedicoResponseDTO buscar(Long id) {
        Medico entity = repository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Medico não encontrado com id: " + id));
        return mapper.toResponseDTO(entity);
    }

    public MedicoResponseDTO criar(MedicoRequestDTO dto) {
        Medico entity = mapper.toEntity(dto);
        Medico salvo = repository.save(entity);
        return mapper.toResponseDTO(salvo);
    }

    public MedicoResponseDTO atualizar(Long id, MedicoRequestDTO dto) {
        if (!repository.existsById(id)) {
            throw new ResourceNotFoundException("Medico não encontrado com id: " + id);
        }
        Medico entity = mapper.toEntity(dto);
        entity.setId(id);
        Medico salvo = repository.save(entity);
        return mapper.toResponseDTO(salvo);
    }

    public void deletar(Long id) {
        if (!repository.existsById(id)) {
            throw new ResourceNotFoundException("Medico não encontrado com id: " + id);
        }
        repository.deleteById(id);
    }
}
