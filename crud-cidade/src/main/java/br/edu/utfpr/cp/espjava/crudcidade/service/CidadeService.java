package br.edu.utfpr.cp.espjava.crudcidade.service;

import br.edu.utfpr.cp.espjava.crudcidade.converter.CidadeConverter;
import br.edu.utfpr.cp.espjava.crudcidade.dto.CidadeDTO;
import br.edu.utfpr.cp.espjava.crudcidade.model.CidadeDAO;
import br.edu.utfpr.cp.espjava.crudcidade.repository.CidadeRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CidadeService {

    private CidadeRepository cidadeRepository;
    private CidadeConverter cidadeConverter;

    public CidadeService(CidadeRepository cidadeRepository, CidadeConverter cidadeConverter) {
        this.cidadeRepository = cidadeRepository;
        this.cidadeConverter = cidadeConverter;
    }

    public void salvarCidade(final CidadeDTO cidadeDTO) {

        cidadeRepository.saveAndFlush(cidadeConverter.convertFromDto(cidadeDTO));
    }

    public void deletarCidade(final String nome, final String estado) {

        var cidadeOpt = cidadeRepository.findByNomeAndEstado(nome, estado);
        cidadeOpt.ifPresent(cidadeRepository::delete);
    }

    public CidadeDTO buscarCidadeByNomeAndEstado(final String nome, final String estado) {

        Optional<CidadeDAO> cidadeDAO = cidadeRepository.findByNomeAndEstado(nome, estado);

        if (cidadeDAO.isPresent()) {
            return cidadeConverter.convertFromEntity(cidadeDAO.get());
        }

        return null;
    }

    public List<CidadeDTO> buscarTodasCidades() {

        return cidadeConverter.createFromEntities(cidadeRepository.findAll());
    }

}
