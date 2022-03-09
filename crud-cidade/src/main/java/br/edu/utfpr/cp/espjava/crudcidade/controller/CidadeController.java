package br.edu.utfpr.cp.espjava.crudcidade.controller;

import br.edu.utfpr.cp.espjava.crudcidade.model.Cidade;
import br.edu.utfpr.cp.espjava.crudcidade.service.CidadeService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
import java.util.HashSet;
import java.util.Set;

@Controller
public class CidadeController {

    private CidadeService cidadeService;

    public CidadeController(final CidadeService cidadeService) {
        this.cidadeService = cidadeService;
    }

    @GetMapping("/")
    public String listar(Model model) {

        model.addAttribute("cidadeList", cidadeService.buscarTodasCidades());
        return "/crud";
    }

    @PostMapping("/criar")
    public String criar(@Valid Cidade cidade, BindingResult bindingResult, Model model) {

        if (bindingResult.hasErrors()) {
            bindingResult
                    .getFieldErrors()
                    .forEach(
                            error -> model.addAttribute(error.getField(), error.getDefaultMessage())
                );

            model.addAttribute("nomeInformado", cidade.getNome());
            model.addAttribute("estadoInformado", cidade.getEstado());
            model.addAttribute("listaCidades", cidadeService.buscarTodasCidades());

            return "/crud";
        } else {

            cidadeService.salvarCidade(new Cidade(cidade.getNome(), cidade.getEstado()));
        }

        return "redirect:/";
    }

    @GetMapping("/excluir")
    public String excluir(@RequestParam String nome, @RequestParam String estado) {

        cidadeService.deletarCidade(nome, estado);
        return "redirect:/";
    }

    @GetMapping("/prepararAlterar")
    public String prepararAlteracao(@RequestParam String nome, @RequestParam String estado, Model model) {

        var cidadeAtual = cidadeService.buscarCidadeByNomeAndEstado(nome, estado);

        if (cidadeAtual.isPresent()) {

            model.addAttribute("cidadeAtual", cidadeAtual.get());
            model.addAttribute("cidadeList", cidadeService.buscarTodasCidades());
        }

        return "/crud";
    }

    @PostMapping("/alterar")
    public String alterar(@RequestParam String nomeAtual, @RequestParam String estadoAtual, Cidade cidade, BindingResult bindingResult, Model model) {

        var cidadeAtual = cidadeService.buscarCidadeByNomeAndEstado(nomeAtual, estadoAtual);

        if (cidadeAtual.isPresent()) {

            var cidadeEncontrada = cidadeAtual.get();
            cidadeEncontrada.setNome(cidade.getNome());
            cidadeEncontrada.setEstado(cidade.getEstado());

            cidadeService.salvarCidade(cidadeEncontrada);
        }

        return "redirect:/";
    }
}
