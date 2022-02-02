package br.edu.utfpr.cp.espjava.crudcidade.model;

public final class Cidade {

    private String nome;
    private String estado;

    public Cidade(final String nome,final String estado) {
        this.nome = nome;
        this.estado = estado;
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
