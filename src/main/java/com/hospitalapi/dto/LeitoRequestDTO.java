package com.hospitalapi.dto;

import jakarta.validation.constraints.*;

public class LeitoRequestDTO {

    @Min(value = 0, message = "numero não pode ser negativo")
    @NotNull(message = "numero não pode ser nulo")
    private Integer numero;
    @NotBlank(message = "ala não pode estar em branco")
    private String ala;
    @NotBlank(message = "tipo não pode estar em branco")
    private String tipo;
    @NotBlank(message = "status não pode estar em branco")
    private String status;
    @NotBlank(message = "descricao não pode estar em branco")
    private String descricao;

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
