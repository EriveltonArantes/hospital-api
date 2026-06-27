package com.hospitalapi.dto;

public class ConsultaResponseDTO {

    private Long id;
    private Long pacienteId;
    private Long medicoId;
    private java.time.LocalDateTime dataAgendamento;
    private String motivo;
    private String observacoes;
    private String status;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
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
