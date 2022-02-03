package br.edu.utfpr.cp.espjava.crudcidade.controller;

import br.edu.utfpr.cp.espjava.crudcidade.model.Cidade;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.HashSet;
import java.util.Set;

@Controller
public class CidadeController {

    private Set<Cidade> cidadeList;

    public CidadeController() {
        cidadeList = new HashSet<>();
    }

    @GetMapping("/")
    public String listar(Model model) {

        model.addAttribute("cidadeList", cidadeList);
        return "/crud";
    }

    @PostMapping("/criar")
    public String criar(Cidade cidade) {

        cidadeList.add(cidade);
        return "redirect:/";
    }

    @GetMapping("/excluir")
    public String excluir(@RequestParam String nome, @RequestParam String estado) {

        cidadeList.removeIf(cidadeAtual ->
                        cidadeAtual.getNome().equals(nome) &&
                        cidadeAtual.getEstado().equals(estado)
        );
        return "redirect:/";
    }

    @GetMapping("/prepararAlterar")
    public String prepararAlteracao(@RequestParam String nome, @RequestParam String estado, Model model) {

        var cidadeAtual= cidadeList
                .stream()
                .filter(cidade ->
                            cidade.getNome().equals(nome) &&
                            cidade.getEstado().equals(estado)
                ).findAny();

        if (cidadeAtual.isPresent()){
            model.addAttribute("cidadeAtual", cidadeAtual.get());
            model.addAttribute("cidadeList", cidadeList);
        }

        return "/crud";
    }

    @PostMapping("/alterar")
    public String alterar(@RequestParam String nomeAtual, @RequestParam String estadoAtual, Cidade cidade) {

        cidadeList.removeIf(cidadeAtual ->
                            cidadeAtual.getNome().equals(nomeAtual) &&
                            cidadeAtual.getEstado().equals(estadoAtual)
        );

        criar(cidade);
        return "redirect:/";
    }
}
