package br.edu.utfpr.cp.espjava.crudcidade.repository;

import br.edu.utfpr.cp.espjava.crudcidade.model.Cidade;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CidadeRepository extends JpaRepository<Cidade, Long> {

    Optional<Cidade> findByNomeAndEstado(String nome, String estado);
}
