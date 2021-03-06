package br.edu.utfpr.cp.espjava.crudcidade.converter;

import br.edu.utfpr.cp.espjava.crudcidade.dto.CidadeDTO;
import br.edu.utfpr.cp.espjava.crudcidade.model.Cidade;
import br.edu.utfpr.cp.espjava.crudcidade.configuration.ConverterGeneric;
import org.springframework.stereotype.Component;

@Component
public class CidadeConverter extends ConverterGeneric<CidadeDTO, Cidade> {

    public CidadeConverter() {
        super(CidadeConverter::convertToEntity, CidadeConverter::convertToDto);
    }

    private static CidadeDTO convertToDto(Cidade cidadeDAO) {

        CidadeDTO cidadeDTO = new CidadeDTO();

        cidadeDTO.setId(cidadeDAO.getId());
        cidadeDTO.setNome(cidadeDAO.getNome());
        cidadeDTO.setEstado(cidadeDAO.getEstado());

        return cidadeDTO;
    }

    private static Cidade convertToEntity(CidadeDTO cidadeDTO) {

        Cidade cidadeDAO = new Cidade();

        cidadeDAO.setId(cidadeDTO.getId());
        cidadeDAO.setNome(cidadeDTO.getNome());
        cidadeDAO.setEstado(cidadeDTO.getEstado());

        return cidadeDAO;
    }
}
