package br.edu.utfpr.cp.espjava.crudcidade.model;

import org.springframework.data.jpa.domain.AbstractPersistable;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Entity(name = "usuario")
public class Usuario extends AbstractPersistable<Long> implements UserDetails {

    private String nome;
    private String senha;

    @ElementCollection(fetch = FetchType.EAGER)
    private List<String> nivelList;

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

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public List<String> getNivelList() {
        return nivelList;
    }

    public void setNivelList(List<String> nivelList) {
        this.nivelList = nivelList;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.nivelList
                .stream()
                .map(nivel -> new SimpleGrantedAuthority(nivel))
                .collect(Collectors.toList());
    }

    @Override
    public String getPassword() {
        return this.senha;
    }

    @Override
    public String getUsername() {
        return this.nome;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
