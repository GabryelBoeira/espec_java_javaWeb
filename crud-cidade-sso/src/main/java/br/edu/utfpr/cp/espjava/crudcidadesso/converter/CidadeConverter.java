package br.edu.utfpr.cp.espjava.crudcidadesso.converter;

import br.edu.utfpr.cp.espjava.crudcidadesso.dto.CidadeDTO;
import br.edu.utfpr.cp.espjava.crudcidadesso.model.Cidade;
import br.edu.utfpr.cp.espjava.crudcidadesso.configuration.ConverterGeneric;
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
