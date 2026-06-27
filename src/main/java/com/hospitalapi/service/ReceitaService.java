package com.hospitalapi.service;

import com.hospitalapi.dto.ReceitaRequestDTO;
import com.hospitalapi.dto.ReceitaResponseDTO;
import com.hospitalapi.exception.ResourceNotFoundException;
import com.hospitalapi.mapper.ReceitaMapper;
import com.hospitalapi.model.Receita;
import com.hospitalapi.repository.ReceitaRepository;
import com.hospitalapi.repository.PacienteRepository;
import com.hospitalapi.model.Paciente;
import com.hospitalapi.repository.MedicoRepository;
import com.hospitalapi.model.Medico;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class ReceitaService {

    @Autowired
    private ReceitaRepository repository;

    @Autowired
    private ReceitaMapper mapper;

    @Autowired
    private PacienteRepository pacienteRepository;

    @Autowired
    private MedicoRepository medicoRepository;

    public List<ReceitaResponseDTO> listar() {
        return repository.findAll().stream().map(mapper::toResponseDTO).collect(Collectors.toList());
    }

    public ReceitaResponseDTO buscar(Long id) {
        Receita entity = repository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Receita não encontrado com id: " + id));
        return mapper.toResponseDTO(entity);
    }

    public ReceitaResponseDTO criar(ReceitaRequestDTO dto) {
        Receita entity = mapper.toEntity(dto);
        Paciente paciente = pacienteRepository.findById(dto.getPacienteId())
            .orElseThrow(() -> new ResourceNotFoundException("Paciente não encontrado com id: " + dto.getPacienteId()));
        entity.setPaciente(paciente);
        Medico medico = medicoRepository.findById(dto.getMedicoId())
            .orElseThrow(() -> new ResourceNotFoundException("Medico não encontrado com id: " + dto.getMedicoId()));
        entity.setMedico(medico);
        Receita salvo = repository.save(entity);
        return mapper.toResponseDTO(salvo);
    }

    public ReceitaResponseDTO atualizar(Long id, ReceitaRequestDTO dto) {
        if (!repository.existsById(id)) {
            throw new ResourceNotFoundException("Receita não encontrado com id: " + id);
        }
        Receita entity = mapper.toEntity(dto);
        entity.setId(id);
        Paciente paciente = pacienteRepository.findById(dto.getPacienteId())
            .orElseThrow(() -> new ResourceNotFoundException("Paciente não encontrado com id: " + dto.getPacienteId()));
        entity.setPaciente(paciente);
        Medico medico = medicoRepository.findById(dto.getMedicoId())
            .orElseThrow(() -> new ResourceNotFoundException("Medico não encontrado com id: " + dto.getMedicoId()));
        entity.setMedico(medico);
        Receita salvo = repository.save(entity);
        return mapper.toResponseDTO(salvo);
    }

    public void deletar(Long id) {
        if (!repository.existsById(id)) {
            throw new ResourceNotFoundException("Receita não encontrado com id: " + id);
        }
        repository.deleteById(id);
    }
}
