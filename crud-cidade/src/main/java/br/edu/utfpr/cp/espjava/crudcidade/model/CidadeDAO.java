package br.edu.utfpr.cp.espjava.crudcidade.model;

import br.edu.utfpr.cp.espjava.crudcidade.dto.CidadeDTO;
import org.springframework.data.jpa.domain.AbstractPersistable;

import javax.persistence.Entity;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Entity(name = "cidade")
public final class CidadeDAO extends AbstractPersistable<Long> {

    @NotBlank
    @Size(min = 5, max = 60)
    private String nome;

    @NotBlank
    @Size(min = 2, max = 2)
    private String estado;

    public CidadeDAO() {}

    public CidadeDAO(final String nome, final String estado) {
        this.nome = nome;
        this.estado = estado;
    }

    @Override
    public void setId(Long id) {
        super.setId(id);
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
