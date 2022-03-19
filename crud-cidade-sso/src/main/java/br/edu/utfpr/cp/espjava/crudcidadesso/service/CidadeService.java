package br.edu.utfpr.cp.espjava.crudcidadesso.service;

import br.edu.utfpr.cp.espjava.crudcidadesso.converter.CidadeConverter;
import br.edu.utfpr.cp.espjava.crudcidadesso.dto.CidadeDTO;
import br.edu.utfpr.cp.espjava.crudcidadesso.model.Cidade;
import br.edu.utfpr.cp.espjava.crudcidadesso.repository.CidadeRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CidadeService {

    private final CidadeRepository cidadeRepository;
    private final CidadeConverter cidadeConverter;

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

        Optional<Cidade> cidadeDAO = cidadeRepository.findByNomeAndEstado(nome, estado);
        return cidadeDAO.map(cidadeConverter::convertFromEntity).orElse(null);
    }

    public List<CidadeDTO> buscarTodasCidades() {

        return cidadeConverter.createFromEntities(cidadeRepository.findAll());
    }

}
