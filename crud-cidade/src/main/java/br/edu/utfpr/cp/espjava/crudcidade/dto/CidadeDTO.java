package br.edu.utfpr.cp.espjava.crudcidade.dto;

import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class CidadeDTO {

    private Long id;

    @NotBlank
    @Size(min = 5, max = 60)
    private String nome;

    @NotBlank
    @Size(min = 2, max = 2)
    private String estado;

    public CidadeDTO() {}

    public CidadeDTO(final String nome, final String estado) {
        this.nome = nome;
        this.estado = estado;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
}
