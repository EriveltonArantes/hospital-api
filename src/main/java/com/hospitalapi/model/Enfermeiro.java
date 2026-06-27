package com.hospitalapi.model;

import jakarta.persistence.*;

@Entity
@Table(name = "enfermeiros")
public class Enfermeiro {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;
    private String coren;
    @Column(unique = true)
    private String email;
    private String turno;
    private String setor;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }
    public String getCoren() { return coren; }
    public void setCoren(String coren) { this.coren = coren; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public String getTurno() { return turno; }
    public void setTurno(String turno) { this.turno = turno; }
    public String getSetor() { return setor; }
    public void setSetor(String setor) { this.setor = setor; }
}
