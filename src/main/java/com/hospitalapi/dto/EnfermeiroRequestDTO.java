package com.hospitalapi.dto;

import jakarta.validation.constraints.*;

public class EnfermeiroRequestDTO {

    @NotBlank(message = "nome não pode estar em branco")
    private String nome;
    @NotBlank(message = "coren não pode estar em branco")
    private String coren;
    @NotBlank(message = "email não pode estar em branco")
    @Email(message = "email precisa ser um e-mail válido")
    private String email;
    @NotBlank(message = "turno não pode estar em branco")
    private String turno;
    @NotBlank(message = "setor não pode estar em branco")
    private String setor;

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
