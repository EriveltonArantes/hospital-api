package com.hospitalapi.controller;

import com.hospitalapi.dto.MedicoRequestDTO;
import com.hospitalapi.dto.MedicoResponseDTO;
import com.hospitalapi.service.MedicoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@Tag(name = "Medico", description = "Gerenciamento de medicos")
@RestController
@RequestMapping("/api/medicos")
public class MedicoController {

    @Autowired
    private MedicoService service;

    @Operation(summary = "Listar todos os Medico")
    @GetMapping
    public List<MedicoResponseDTO> listar(@RequestParam(required = false) String nome) {
        List<MedicoResponseDTO> resultado = service.listar();
        if (nome != null && !nome.isBlank()) {
            resultado = resultado.stream().filter(item -> item.getNome() != null &&
                item.getNome().toLowerCase().contains(nome.toLowerCase()))
                .collect(java.util.stream.Collectors.toList());
        }
        return resultado;
    }

    @Operation(summary = "Buscar Medico por ID")
    @GetMapping("/{id}")
    public MedicoResponseDTO buscar(@PathVariable Long id) { return service.buscar(id); }

    @Operation(summary = "Criar Medico")
    @PostMapping
    public ResponseEntity<MedicoResponseDTO> criar(@Valid @RequestBody MedicoRequestDTO medico) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.criar(medico));
    }

    @Operation(summary = "Atualizar Medico")
    @PutMapping("/{id}")
    public MedicoResponseDTO atualizar(@PathVariable Long id, @Valid @RequestBody MedicoRequestDTO medico) {
        return service.atualizar(id, medico);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @Operation(summary = "Excluir Medico")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        service.deletar(id);
        return ResponseEntity.noContent().build();
    }
}
