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

    public CidadeService(final CidadeRepository cidadeRepository) {
        this.cidadeRepository = cidadeRepository;
    }

    public void salvarCidade(final CidadeDTO cidade) {

        cidadeRepository.saveAndFlush(CidadeConverter.converterCidadeDTO(cidade));
    }

    public void deletarCidade(final String nome, final String estado) {

        var cidadeOpt = cidadeRepository.findByNomeAndEstado(nome, estado);
        cidadeOpt.ifPresent(cidadeRepository::delete);
    }

    public CidadeDTO buscarCidadeByNomeAndEstado(final String nome, final String estado) {

        Optional<CidadeDAO> cidadeDAO = cidadeRepository.findByNomeAndEstado(nome, estado);

        if (cidadeDAO.isPresent()) {
            return CidadeConverter.converterCidadeDAO(cidadeDAO.get());
        }

        return null;
    }

    public List<CidadeDTO> buscarTodasCidades() {

        return CidadeConverter.mapAll(cidadeRepository.findAll(), CidadeDTO.class);
    }

}
