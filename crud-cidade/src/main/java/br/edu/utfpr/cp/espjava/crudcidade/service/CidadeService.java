package br.edu.utfpr.cp.espjava.crudcidade.service;

import br.edu.utfpr.cp.espjava.crudcidade.model.Cidade;
import br.edu.utfpr.cp.espjava.crudcidade.repository.CidadeRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CidadeService {

    private CidadeRepository cidadeRepository;

    public CidadeService(final CidadeRepository cidadeRepository) {
        this.cidadeRepository = cidadeRepository;
    }

    public void salvarCidade(final Cidade cidade) {
        cidadeRepository.saveAndFlush(cidade);
    }

    public void deletarCidade(final String nome, final String estado) {

        var cidadeOpt = buscarCidadeByNomeAndEstado(nome, estado);
        cidadeOpt.ifPresent(cidadeRepository::delete);
    }

    public Optional<Cidade> buscarCidadeByNomeAndEstado(final String nome, final String estado) {

        return cidadeRepository.findByNomeAndEstado(nome, estado);
    }

    public List<Cidade> buscarTodasCidades() {
        return cidadeRepository.findAll();
    }

}
