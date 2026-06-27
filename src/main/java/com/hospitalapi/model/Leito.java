package com.hospitalapi.model;

import jakarta.persistence.*;

@Entity
@Table(name = "leitos")
public class Leito {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer numero;
    @Column(nullable = false)
    private String ala;
    @Column(nullable = false)
    private String tipo;
    @Column(nullable = false)
    private String status;
    @Column(columnDefinition = "TEXT", nullable = false)
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
