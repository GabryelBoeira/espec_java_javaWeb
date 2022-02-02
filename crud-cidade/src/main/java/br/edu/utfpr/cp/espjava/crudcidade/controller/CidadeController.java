package br.edu.utfpr.cp.espjava.crudcidade.controller;

import br.edu.utfpr.cp.espjava.crudcidade.model.Cidade;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Set;

@Controller
public class CidadeController {

    @GetMapping("/")
    public String listar(Model model) {

        var cidadeSet = Set.of(
            new Cidade("Curitiba", "PR"),
            new Cidade("Porto Alegre", "RS"),
            new Cidade("Belo Horizonte", "MG")
        );

        model.addAttribute("cidadeList", cidadeSet);

        return "/crud";
    }
}
