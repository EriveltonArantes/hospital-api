package com.hospitalapi.dto;

import jakarta.validation.constraints.*;

public class ReceitaRequestDTO {

    @NotNull(message = "paciente não pode ser nulo")
    private Long pacienteId;
    @NotNull(message = "medico não pode ser nulo")
    private Long medicoId;
    @NotNull(message = "data emissao não pode ser nulo")
    private java.time.LocalDateTime dataEmissao;
    @NotNull(message = "validade não pode ser nulo")
    private java.time.LocalDate validade;
    @NotBlank(message = "observacoes não pode estar em branco")
    private String observacoes;

    public Long getPacienteId() { return pacienteId; }
    public void setPacienteId(Long pacienteId) { this.pacienteId = pacienteId; }
    public Long getMedicoId() { return medicoId; }
    public void setMedicoId(Long medicoId) { this.medicoId = medicoId; }
    public java.time.LocalDateTime getDataEmissao() { return dataEmissao; }
    public void setDataEmissao(java.time.LocalDateTime dataEmissao) { this.dataEmissao = dataEmissao; }
    public java.time.LocalDate getValidade() { return validade; }
    public void setValidade(java.time.LocalDate validade) { this.validade = validade; }
    public String getObservacoes() { return observacoes; }
    public void setObservacoes(String observacoes) { this.observacoes = observacoes; }
}
