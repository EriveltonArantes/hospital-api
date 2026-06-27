package com.hospitalapi.controller;

import com.hospitalapi.dto.ExameRequestDTO;
import com.hospitalapi.dto.ExameResponseDTO;
import com.hospitalapi.service.ExameService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/exames")
public class ExameController {

    @Autowired
    private ExameService service;

    @GetMapping
    public List<ExameResponseDTO> listar(@RequestParam(required = false) String tipo, @RequestParam(required = false) Long pacienteId, @RequestParam(required = false) Long medicoId) {
        List<ExameResponseDTO> resultado = service.listar();
        if (tipo != null && !tipo.isBlank()) {
            resultado = resultado.stream().filter(item -> item.getTipo() != null &&
                item.getTipo().toLowerCase().contains(tipo.toLowerCase()))
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
    public ExameResponseDTO buscar(@PathVariable Long id) { return service.buscar(id); }

    @PostMapping
    public ResponseEntity<ExameResponseDTO> criar(@Valid @RequestBody ExameRequestDTO exame) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.criar(exame));
    }

    @PutMapping("/{id}")
    public ExameResponseDTO atualizar(@PathVariable Long id, @Valid @RequestBody ExameRequestDTO exame) {
        return service.atualizar(id, exame);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        service.deletar(id);
        return ResponseEntity.noContent().build();
    }
}
