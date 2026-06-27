package com.hospitalapi.controller;

import com.hospitalapi.dto.InternacaoRequestDTO;
import com.hospitalapi.dto.InternacaoResponseDTO;
import com.hospitalapi.service.InternacaoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@Tag(name = "Internacao", description = "Gerenciamento de internacaos")
@RestController
@RequestMapping("/api/internacaos")
public class InternacaoController {

    @Autowired
    private InternacaoService service;

    @Operation(summary = "Listar todos os Internacao")
    @GetMapping
    public List<InternacaoResponseDTO> listar(@RequestParam(required = false) String diagnostico, @RequestParam(required = false) Long pacienteId, @RequestParam(required = false) Long medicoId, @RequestParam(required = false) Long leitoId) {
        List<InternacaoResponseDTO> resultado = service.listar();
        if (diagnostico != null && !diagnostico.isBlank()) {
            resultado = resultado.stream().filter(item -> item.getDiagnostico() != null &&
                item.getDiagnostico().toLowerCase().contains(diagnostico.toLowerCase()))
                .collect(java.util.stream.Collectors.toList());
        }
        if (pacienteId != null) {
            resultado = resultado.stream().filter(item -> pacienteId.equals(item.getPacienteId())).collect(java.util.stream.Collectors.toList());
        }
        if (medicoId != null) {
            resultado = resultado.stream().filter(item -> medicoId.equals(item.getMedicoId())).collect(java.util.stream.Collectors.toList());
        }
        if (leitoId != null) {
            resultado = resultado.stream().filter(item -> leitoId.equals(item.getLeitoId())).collect(java.util.stream.Collectors.toList());
        }
        return resultado;
    }

    @Operation(summary = "Buscar Internacao por ID")
    @GetMapping("/{id}")
    public InternacaoResponseDTO buscar(@PathVariable Long id) { return service.buscar(id); }

    @Operation(summary = "Criar Internacao")
    @PostMapping
    public ResponseEntity<InternacaoResponseDTO> criar(@Valid @RequestBody InternacaoRequestDTO internacao) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.criar(internacao));
    }

    @Operation(summary = "Atualizar Internacao")
    @PutMapping("/{id}")
    public InternacaoResponseDTO atualizar(@PathVariable Long id, @Valid @RequestBody InternacaoRequestDTO internacao) {
        return service.atualizar(id, internacao);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @Operation(summary = "Excluir Internacao")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        service.deletar(id);
        return ResponseEntity.noContent().build();
    }
}
