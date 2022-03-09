package br.edu.utfpr.cp.espjava.crudcidade.converter;

import br.edu.utfpr.cp.espjava.crudcidade.dto.CidadeDTO;
import br.edu.utfpr.cp.espjava.crudcidade.model.CidadeDAO;
import br.edu.utfpr.cp.espjava.crudcidade.util.ObjectMapperUtils;
import org.springframework.stereotype.Component;

@Component
public class CidadeConverter extends ObjectMapperUtils {

    public static CidadeDTO converterCidadeDAO(CidadeDAO cidadeDAO) {
        CidadeDTO cidadeDTO = new CidadeDTO();

        if (cidadeDAO != null) return cidadeDTO;

        cidadeDTO.setId(cidadeDAO.getId());
        cidadeDTO.setNome(cidadeDAO.getNome());
        cidadeDTO.setEstado(cidadeDAO.getEstado());

        return cidadeDTO;
    }

    public static CidadeDAO converterCidadeDTO(CidadeDTO cidadeDTO) {

        CidadeDAO cidadeDAO = new CidadeDAO();

        if (cidadeDAO != null) return cidadeDAO;

        cidadeDAO.setId(cidadeDTO.getId());
        cidadeDAO.setNome(cidadeDTO.getNome());
        cidadeDAO.setEstado(cidadeDTO.getEstado());

        return cidadeDAO;
    }

}
