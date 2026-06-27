package com.hospitalapi.controller;

import com.hospitalapi.model.Paciente;
import com.hospitalapi.model.Medico;
import com.hospitalapi.model.Leito;
import com.hospitalapi.model.Internacao;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.LinkedHashMap;
import java.util.Map;

@Tag(name = "Dashboard", description = "KPIs e totais do sistema")
@RestController
@RequestMapping("/api/dashboard")
public class DashboardController {

    @Autowired
    private com.hospitalapi.repository.PacienteRepository pacienteRepository;

    @Autowired
    private com.hospitalapi.repository.MedicoRepository medicoRepository;

    @Autowired
    private com.hospitalapi.repository.LeitoRepository leitoRepository;

    @Autowired
    private com.hospitalapi.repository.InternacaoRepository internacaoRepository;

    @Operation(summary = "Resumo com totais, somas e gráficos de status")
    @Transactional(readOnly = true)
    @GetMapping("/resumo")
    public Map<String, Object> resumo() {
        Map<String, Object> resumo = new LinkedHashMap<>();
        resumo.put("totalPaciente", pacienteRepository.count());
        resumo.put("totalMedico", medicoRepository.count());
        resumo.put("totalLeito", leitoRepository.count());
        resumo.put("somaNumeroLeito", leitoRepository.findAll().stream().filter(e -> e.getNumero() != null).mapToInt(e -> e.getNumero()).sum());
        resumo.put("graficoLeito", leitoRepository.findAll().stream()
            .collect(java.util.stream.Collectors.groupingBy(
                item -> String.valueOf(item.getStatus()),
                java.util.LinkedHashMap::new,
                java.util.stream.Collectors.counting())));
        resumo.put("totalInternacao", internacaoRepository.count());
        resumo.put("graficoInternacao", internacaoRepository.findAll().stream()
            .collect(java.util.stream.Collectors.groupingBy(
                item -> String.valueOf(item.getStatus()),
                java.util.LinkedHashMap::new,
                java.util.stream.Collectors.counting())));
        return resumo;
    }
}
