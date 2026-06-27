package com.hospitalapi.dto;

import jakarta.validation.constraints.*;

public class ExameRequestDTO {

    @NotNull(message = "paciente não pode ser nulo")
    private Long pacienteId;
    @NotNull(message = "medico não pode ser nulo")
    private Long medicoId;
    @NotNull(message = "data realizacao não pode ser nulo")
    private java.time.LocalDateTime dataRealizacao;
    @NotBlank(message = "tipo não pode estar em branco")
    private String tipo;
    @NotBlank(message = "resultado não pode estar em branco")
    private String resultado;
    @NotBlank(message = "laudo não pode estar em branco")
    private String laudo;
    @NotBlank(message = "status não pode estar em branco")
    private String status;

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
