package com.hospitalapi.dto;

import jakarta.validation.constraints.*;

public class InternacaoRequestDTO {

    @NotNull(message = "PacienteId é obrigatório")
    @Positive(message = "PacienteId deve ser um ID válido (positivo)")
    private Long pacienteId;
    @NotNull(message = "MedicoId é obrigatório")
    @Positive(message = "MedicoId deve ser um ID válido (positivo)")
    private Long medicoId;
    @NotNull(message = "LeitoId é obrigatório")
    @Positive(message = "LeitoId deve ser um ID válido (positivo)")
    private Long leitoId;
    @NotNull(message = "data entrada não pode ser nulo")
    private java.time.LocalDateTime dataEntrada;
    @NotNull(message = "data saida não pode ser nulo")
    private java.time.LocalDateTime dataSaida;
    @NotBlank(message = "diagnostico não pode estar em branco")
    private String diagnostico;
    @NotBlank(message = "status não pode estar em branco")
    private String status;

    private String observacoes;

    public Long getPacienteId() { return pacienteId; }
    public void setPacienteId(Long pacienteId) { this.pacienteId = pacienteId; }
    public Long getMedicoId() { return medicoId; }
    public void setMedicoId(Long medicoId) { this.medicoId = medicoId; }
    public Long getLeitoId() { return leitoId; }
    public void setLeitoId(Long leitoId) { this.leitoId = leitoId; }
    public java.time.LocalDateTime getDataEntrada() { return dataEntrada; }
    public void setDataEntrada(java.time.LocalDateTime dataEntrada) { this.dataEntrada = dataEntrada; }
    public java.time.LocalDateTime getDataSaida() { return dataSaida; }
    public void setDataSaida(java.time.LocalDateTime dataSaida) { this.dataSaida = dataSaida; }
    public String getDiagnostico() { return diagnostico; }
    public void setDiagnostico(String diagnostico) { this.diagnostico = diagnostico; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
    public String getObservacoes() { return observacoes; }
    public void setObservacoes(String observacoes) { this.observacoes = observacoes; }
}
