package com.hospitalapi.model;

import jakarta.persistence.*;

@Entity
@Table(name = "exames")
public class Exame {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "paciente_id")
    private Paciente paciente;
    @ManyToOne
    @JoinColumn(name = "medico_id")
    private Medico medico;
    private java.time.LocalDateTime dataRealizacao;
    private String tipo;
    private String resultado;
    private String laudo;
    private String status;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Paciente getPaciente() { return paciente; }
    public void setPaciente(Paciente paciente) { this.paciente = paciente; }
    public Medico getMedico() { return medico; }
    public void setMedico(Medico medico) { this.medico = medico; }
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
