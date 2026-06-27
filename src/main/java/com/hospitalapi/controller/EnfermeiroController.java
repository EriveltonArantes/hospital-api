package com.hospitalapi.controller;

import com.hospitalapi.dto.EnfermeiroRequestDTO;
import com.hospitalapi.dto.EnfermeiroResponseDTO;
import com.hospitalapi.service.EnfermeiroService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/enfermeiros")
public class EnfermeiroController {

    @Autowired
    private EnfermeiroService service;

    @GetMapping
    public List<EnfermeiroResponseDTO> listar(@RequestParam(required = false) String nome) {
        List<EnfermeiroResponseDTO> resultado = service.listar();
        if (nome != null && !nome.isBlank()) {
            resultado = resultado.stream().filter(item -> item.getNome() != null &&
                item.getNome().toLowerCase().contains(nome.toLowerCase()))
                .collect(java.util.stream.Collectors.toList());
        }
        return resultado;
    }

    @GetMapping("/{id}")
    public EnfermeiroResponseDTO buscar(@PathVariable Long id) { return service.buscar(id); }

    @PostMapping
    public ResponseEntity<EnfermeiroResponseDTO> criar(@Valid @RequestBody EnfermeiroRequestDTO enfermeiro) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.criar(enfermeiro));
    }

    @PutMapping("/{id}")
    public EnfermeiroResponseDTO atualizar(@PathVariable Long id, @Valid @RequestBody EnfermeiroRequestDTO enfermeiro) {
        return service.atualizar(id, enfermeiro);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        service.deletar(id);
        return ResponseEntity.noContent().build();
    }
}
