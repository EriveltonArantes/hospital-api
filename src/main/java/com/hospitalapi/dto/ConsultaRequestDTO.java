package com.hospitalapi.dto;

import jakarta.validation.constraints.*;

public class ConsultaRequestDTO {

    @NotNull(message = "paciente não pode ser nulo")
    private Long pacienteId;
    @NotNull(message = "medico não pode ser nulo")
    private Long medicoId;
    @FutureOrPresent(message = "data agendamento não pode ser retroativo")
    @NotNull(message = "data agendamento não pode ser nulo")
    private java.time.LocalDateTime dataAgendamento;
    @NotBlank(message = "motivo não pode estar em branco")
    private String motivo;
    @NotBlank(message = "observacoes não pode estar em branco")
    private String observacoes;
    @NotBlank(message = "status não pode estar em branco")
    private String status;

    public Long getPacienteId() { return pacienteId; }
    public void setPacienteId(Long pacienteId) { this.pacienteId = pacienteId; }
    public Long getMedicoId() { return medicoId; }
    public void setMedicoId(Long medicoId) { this.medicoId = medicoId; }
    public java.time.LocalDateTime getDataAgendamento() { return dataAgendamento; }
    public void setDataAgendamento(java.time.LocalDateTime dataAgendamento) { this.dataAgendamento = dataAgendamento; }
    public String getMotivo() { return motivo; }
    public void setMotivo(String motivo) { this.motivo = motivo; }
    public String getObservacoes() { return observacoes; }
    public void setObservacoes(String observacoes) { this.observacoes = observacoes; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
}
