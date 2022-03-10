package br.edu.utfpr.cp.espjava.crudcidade.converter;

import br.edu.utfpr.cp.espjava.crudcidade.dto.CidadeDTO;
import br.edu.utfpr.cp.espjava.crudcidade.model.CidadeDAO;
import br.edu.utfpr.cp.espjava.crudcidade.configuration.ConverterGeneric;
import org.springframework.stereotype.Component;

@Component
public class CidadeConverter extends ConverterGeneric<CidadeDTO, CidadeDAO> {

    public CidadeConverter() {
        super(CidadeConverter::convertToEntity, CidadeConverter::convertToDto);
    }

    private static CidadeDTO convertToDto(CidadeDAO cidadeDAO) {

        CidadeDTO cidadeDTO = new CidadeDTO();

        cidadeDTO.setId(cidadeDAO.getId());
        cidadeDTO.setNome(cidadeDAO.getNome());
        cidadeDTO.setEstado(cidadeDAO.getEstado());

        return cidadeDTO;
    }

    private static CidadeDAO convertToEntity(CidadeDTO cidadeDTO) {

        CidadeDAO cidadeDAO = new CidadeDAO();

        cidadeDAO.setId(cidadeDTO.getId());
        cidadeDAO.setNome(cidadeDTO.getNome());
        cidadeDAO.setEstado(cidadeDTO.getEstado());

        return cidadeDAO;
    }
}
