package br.edu.utfpr.cp.espjava.crudcidade.repository;

import br.edu.utfpr.cp.espjava.crudcidade.model.CidadeDAO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CidadeRepository extends JpaRepository<CidadeDAO, Long> {

    Optional<CidadeDAO> findByNomeAndEstado(String nome, String estado);
}
