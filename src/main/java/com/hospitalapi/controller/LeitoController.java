package com.hospitalapi.controller;

import com.hospitalapi.dto.LeitoRequestDTO;
import com.hospitalapi.dto.LeitoResponseDTO;
import com.hospitalapi.service.LeitoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@Tag(name = "Leito", description = "Gerenciamento de leitos")
@RestController
@RequestMapping("/api/leitos")
public class LeitoController {

    @Autowired
    private LeitoService service;

    @Operation(summary = "Listar todos os Leito")
    @GetMapping
    public List<LeitoResponseDTO> listar(@RequestParam(required = false) String descricao) {
        List<LeitoResponseDTO> resultado = service.listar();
        if (descricao != null && !descricao.isBlank()) {
            resultado = resultado.stream().filter(item -> item.getDescricao() != null &&
                item.getDescricao().toLowerCase().contains(descricao.toLowerCase()))
                .collect(java.util.stream.Collectors.toList());
        }
        return resultado;
    }

    @Operation(summary = "Buscar Leito por ID")
    @GetMapping("/{id}")
    public LeitoResponseDTO buscar(@PathVariable Long id) { return service.buscar(id); }

    @Operation(summary = "Criar Leito")
    @PostMapping
    public ResponseEntity<LeitoResponseDTO> criar(@Valid @RequestBody LeitoRequestDTO leito) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.criar(leito));
    }

    @Operation(summary = "Atualizar Leito")
    @PutMapping("/{id}")
    public LeitoResponseDTO atualizar(@PathVariable Long id, @Valid @RequestBody LeitoRequestDTO leito) {
        return service.atualizar(id, leito);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @Operation(summary = "Excluir Leito")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        service.deletar(id);
        return ResponseEntity.noContent().build();
    }
}
