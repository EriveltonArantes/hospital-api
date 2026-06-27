package com.hospitalapi.service;

import com.hospitalapi.dto.LeitoRequestDTO;
import com.hospitalapi.dto.LeitoResponseDTO;
import com.hospitalapi.exception.ResourceNotFoundException;
import com.hospitalapi.mapper.LeitoMapper;
import com.hospitalapi.model.Leito;
import com.hospitalapi.repository.LeitoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class LeitoService {

    @Autowired
    private LeitoRepository repository;

    @Autowired
    private LeitoMapper mapper;

    @Transactional(readOnly = true)
    public List<LeitoResponseDTO> listar() {
        return repository.findAll().stream().map(mapper::toResponseDTO).collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public LeitoResponseDTO buscar(Long id) {
        Leito entity = repository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Leito não encontrado com id: " + id));
        return mapper.toResponseDTO(entity);
    }

    public LeitoResponseDTO criar(LeitoRequestDTO dto) {
        Leito entity = mapper.toEntity(dto);
        Leito salvo = repository.save(entity);
        return mapper.toResponseDTO(salvo);
    }

    public LeitoResponseDTO atualizar(Long id, LeitoRequestDTO dto) {
        if (!repository.existsById(id)) {
            throw new ResourceNotFoundException("Leito não encontrado com id: " + id);
        }
        Leito entity = mapper.toEntity(dto);
        entity.setId(id);
        Leito salvo = repository.save(entity);
        return mapper.toResponseDTO(salvo);
    }

    public void deletar(Long id) {
        if (!repository.existsById(id)) {
            throw new ResourceNotFoundException("Leito não encontrado com id: " + id);
        }
        repository.deleteById(id);
    }
}
