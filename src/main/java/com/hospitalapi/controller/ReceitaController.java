package com.hospitalapi.controller;

import com.hospitalapi.dto.ReceitaRequestDTO;
import com.hospitalapi.dto.ReceitaResponseDTO;
import com.hospitalapi.service.ReceitaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/receitas")
public class ReceitaController {

    @Autowired
    private ReceitaService service;

    @GetMapping
    public List<ReceitaResponseDTO> listar(@RequestParam(required = false) String observacoes, @RequestParam(required = false) Long pacienteId, @RequestParam(required = false) Long medicoId) {
        List<ReceitaResponseDTO> resultado = service.listar();
        if (observacoes != null && !observacoes.isBlank()) {
            resultado = resultado.stream().filter(item -> item.getObservacoes() != null &&
                item.getObservacoes().toLowerCase().contains(observacoes.toLowerCase()))
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
    public ReceitaResponseDTO buscar(@PathVariable Long id) { return service.buscar(id); }

    @PostMapping
    public ResponseEntity<ReceitaResponseDTO> criar(@Valid @RequestBody ReceitaRequestDTO receita) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.criar(receita));
    }

    @PutMapping("/{id}")
    public ReceitaResponseDTO atualizar(@PathVariable Long id, @Valid @RequestBody ReceitaRequestDTO receita) {
        return service.atualizar(id, receita);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        service.deletar(id);
        return ResponseEntity.noContent().build();
    }
}
