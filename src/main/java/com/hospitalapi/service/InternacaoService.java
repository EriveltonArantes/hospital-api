package com.hospitalapi.service;

import com.hospitalapi.dto.InternacaoRequestDTO;
import com.hospitalapi.dto.InternacaoResponseDTO;
import com.hospitalapi.exception.ResourceNotFoundException;
import com.hospitalapi.mapper.InternacaoMapper;
import com.hospitalapi.model.Internacao;
import com.hospitalapi.repository.InternacaoRepository;
import com.hospitalapi.repository.PacienteRepository;
import com.hospitalapi.model.Paciente;
import com.hospitalapi.repository.MedicoRepository;
import com.hospitalapi.model.Medico;
import com.hospitalapi.repository.LeitoRepository;
import com.hospitalapi.model.Leito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class InternacaoService {

    @Autowired
    private InternacaoRepository repository;

    @Autowired
    private InternacaoMapper mapper;

    @Autowired
    private PacienteRepository pacienteRepository;

    @Autowired
    private MedicoRepository medicoRepository;

    @Autowired
    private LeitoRepository leitoRepository;

    @Transactional(readOnly = true)
    public List<InternacaoResponseDTO> listar() {
        return repository.findAll().stream().map(mapper::toResponseDTO).collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public InternacaoResponseDTO buscar(Long id) {
        Internacao entity = repository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Internacao não encontrado com id: " + id));
        return mapper.toResponseDTO(entity);
    }

    public InternacaoResponseDTO criar(InternacaoRequestDTO dto) {
        Internacao entity = mapper.toEntity(dto);
        Paciente paciente = pacienteRepository.findById(dto.getPacienteId())
            .orElseThrow(() -> new ResourceNotFoundException("Paciente não encontrado com id: " + dto.getPacienteId()));
        entity.setPaciente(paciente);
        Medico medico = medicoRepository.findById(dto.getMedicoId())
            .orElseThrow(() -> new ResourceNotFoundException("Medico não encontrado com id: " + dto.getMedicoId()));
        entity.setMedico(medico);
        Leito leito = leitoRepository.findById(dto.getLeitoId())
            .orElseThrow(() -> new ResourceNotFoundException("Leito não encontrado com id: " + dto.getLeitoId()));
        entity.setLeito(leito);
        Internacao salvo = repository.save(entity);
        return mapper.toResponseDTO(salvo);
    }

    public InternacaoResponseDTO atualizar(Long id, InternacaoRequestDTO dto) {
        if (!repository.existsById(id)) {
            throw new ResourceNotFoundException("Internacao não encontrado com id: " + id);
        }
        Internacao entity = mapper.toEntity(dto);
        entity.setId(id);
        Paciente paciente = pacienteRepository.findById(dto.getPacienteId())
            .orElseThrow(() -> new ResourceNotFoundException("Paciente não encontrado com id: " + dto.getPacienteId()));
        entity.setPaciente(paciente);
        Medico medico = medicoRepository.findById(dto.getMedicoId())
            .orElseThrow(() -> new ResourceNotFoundException("Medico não encontrado com id: " + dto.getMedicoId()));
        entity.setMedico(medico);
        Leito leito = leitoRepository.findById(dto.getLeitoId())
            .orElseThrow(() -> new ResourceNotFoundException("Leito não encontrado com id: " + dto.getLeitoId()));
        entity.setLeito(leito);
        Internacao salvo = repository.save(entity);
        return mapper.toResponseDTO(salvo);
    }

    public void deletar(Long id) {
        if (!repository.existsById(id)) {
            throw new ResourceNotFoundException("Internacao não encontrado com id: " + id);
        }
        repository.deleteById(id);
    }
}
