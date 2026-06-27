package com.hospitalapi.service;

import com.hospitalapi.dto.ConsultaRequestDTO;
import com.hospitalapi.dto.ConsultaResponseDTO;
import com.hospitalapi.exception.ResourceNotFoundException;
import com.hospitalapi.mapper.ConsultaMapper;
import com.hospitalapi.model.Consulta;
import com.hospitalapi.repository.ConsultaRepository;
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
public class ConsultaService {

    @Autowired
    private ConsultaRepository repository;

    @Autowired
    private ConsultaMapper mapper;

    @Autowired
    private PacienteRepository pacienteRepository;

    @Autowired
    private MedicoRepository medicoRepository;

    public List<ConsultaResponseDTO> listar() {
        return repository.findAll().stream().map(mapper::toResponseDTO).collect(Collectors.toList());
    }

    public ConsultaResponseDTO buscar(Long id) {
        Consulta entity = repository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Consulta não encontrado com id: " + id));
        return mapper.toResponseDTO(entity);
    }

    public ConsultaResponseDTO criar(ConsultaRequestDTO dto) {
        Consulta entity = mapper.toEntity(dto);
        Paciente paciente = pacienteRepository.findById(dto.getPacienteId())
            .orElseThrow(() -> new ResourceNotFoundException("Paciente não encontrado com id: " + dto.getPacienteId()));
        entity.setPaciente(paciente);
        Medico medico = medicoRepository.findById(dto.getMedicoId())
            .orElseThrow(() -> new ResourceNotFoundException("Medico não encontrado com id: " + dto.getMedicoId()));
        entity.setMedico(medico);
        Consulta salvo = repository.save(entity);
        return mapper.toResponseDTO(salvo);
    }

    public ConsultaResponseDTO atualizar(Long id, ConsultaRequestDTO dto) {
        if (!repository.existsById(id)) {
            throw new ResourceNotFoundException("Consulta não encontrado com id: " + id);
        }
        Consulta entity = mapper.toEntity(dto);
        entity.setId(id);
        Paciente paciente = pacienteRepository.findById(dto.getPacienteId())
            .orElseThrow(() -> new ResourceNotFoundException("Paciente não encontrado com id: " + dto.getPacienteId()));
        entity.setPaciente(paciente);
        Medico medico = medicoRepository.findById(dto.getMedicoId())
            .orElseThrow(() -> new ResourceNotFoundException("Medico não encontrado com id: " + dto.getMedicoId()));
        entity.setMedico(medico);
        Consulta salvo = repository.save(entity);
        return mapper.toResponseDTO(salvo);
    }

    public void deletar(Long id) {
        if (!repository.existsById(id)) {
            throw new ResourceNotFoundException("Consulta não encontrado com id: " + id);
        }
        repository.deleteById(id);
    }
}
