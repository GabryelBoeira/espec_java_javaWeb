package br.edu.utfpr.cp.espjava.crudcidade.repository;

import br.edu.utfpr.cp.espjava.crudcidade.model.Usuario;
import org.springframework.data.domain.Example;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    Optional<Usuario> findByNome(String nome);
}
