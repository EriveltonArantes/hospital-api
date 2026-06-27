package com.hospitalapi.dto;

public class ExameResponseDTO {

    private Long id;
    private Long pacienteId;
    private Long medicoId;
    private java.time.LocalDateTime dataRealizacao;
    private String tipo;
    private String resultado;
    private String laudo;
    private String status;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public Long getPacienteId() { return pacienteId; }
    public void setPacienteId(Long pacienteId) { this.pacienteId = pacienteId; }
    public Long getMedicoId() { return medicoId; }
    public void setMedicoId(Long medicoId) { this.medicoId = medicoId; }
    public java.time.LocalDateTime getDataRealizacao() { return dataRealizacao; }
    public void setDataRealizacao(java.time.LocalDateTime dataRealizacao) { this.dataRealizacao = dataRealizacao; }
    public String getTipo() { return tipo; }
    public void setTipo(String tipo) { this.tipo = tipo; }
    public String getResultado() { return resultado; }
    public void setResultado(String resultado) { this.resultado = resultado; }
    public String getLaudo() { return laudo; }
    public void setLaudo(String laudo) { this.laudo = laudo; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
}
