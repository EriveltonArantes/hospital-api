package com.hospitalapi.dto;

public class LeitoResponseDTO {

    private Long id;
    private Integer numero;
    private String ala;
    private String tipo;
    private String status;
    private String descricao;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public Integer getNumero() { return numero; }
    public void setNumero(Integer numero) { this.numero = numero; }
    public String getAla() { return ala; }
    public void setAla(String ala) { this.ala = ala; }
    public String getTipo() { return tipo; }
    public void setTipo(String tipo) { this.tipo = tipo; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
    public String getDescricao() { return descricao; }
    public void setDescricao(String descricao) { this.descricao = descricao; }
}
