package com.hospitalapi.controller;

import com.hospitalapi.dto.ConsultaRequestDTO;
import com.hospitalapi.dto.ConsultaResponseDTO;
import com.hospitalapi.service.ConsultaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/consultas")
public class ConsultaController {

    @Autowired
    private ConsultaService service;

    @GetMapping
    public List<ConsultaResponseDTO> listar(@RequestParam(required = false) String motivo, @RequestParam(required = false) Long pacienteId, @RequestParam(required = false) Long medicoId) {
        List<ConsultaResponseDTO> resultado = service.listar();
        if (motivo != null && !motivo.isBlank()) {
            resultado = resultado.stream().filter(item -> item.getMotivo() != null &&
                item.getMotivo().toLowerCase().contains(motivo.toLowerCase()))
                .collect(java.util.stream.Collectors.toList());
        }
        if (pacienteId != null) {
            resultado = resultado.stream().filter(item -> pacienteId.equals(item.getPacienteId())).collect(java.util.stream.Collectors.toList());
        }
        if (medicoId != null) {
            resultado = resultado.stream().filter(item -> medicoId.equals(item.getMedicoId())).collect(java.util.stream.Collectors.toList());
        }
        return resultado;
    }

    @GetMapping("/{id}")
    public ConsultaResponseDTO buscar(@PathVariable Long id) { return service.buscar(id); }

    @PostMapping
    public ResponseEntity<ConsultaResponseDTO> criar(@Valid @RequestBody ConsultaRequestDTO consulta) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.criar(consulta));
    }

    @PutMapping("/{id}")
    public ConsultaResponseDTO atualizar(@PathVariable Long id, @Valid @RequestBody ConsultaRequestDTO consulta) {
        return service.atualizar(id, consulta);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        service.deletar(id);
        return ResponseEntity.noContent().build();
    }
}
