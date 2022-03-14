package br.edu.utfpr.cp.espjava.crudcidade.controller;

import br.edu.utfpr.cp.espjava.crudcidade.dto.CidadeDTO;
import br.edu.utfpr.cp.espjava.crudcidade.service.CidadeService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.security.Principal;
import java.time.LocalDateTime;

@Controller
public class CidadeController {

    private CidadeService cidadeService;

    public CidadeController(final CidadeService cidadeService) {
        this.cidadeService = cidadeService;
    }

    @GetMapping("/")
    public String listar(Model model, Principal usuario, HttpSession session, HttpServletResponse response) {

        response.addCookie(new Cookie("listar", LocalDateTime.now().toString()));
        model.addAttribute("cidadeList", cidadeService.buscarTodasCidades());
        session.setAttribute("usuarioAtual", usuario.getName());

        return "/crud";
    }

    @PostMapping("/criar")
    public String criar(@Valid CidadeDTO cidadeDTO, BindingResult bindingResult, Model model, HttpServletResponse response) {

        response.addCookie(new Cookie("criar", LocalDateTime.now().toString()));

        if (bindingResult.hasErrors()) {
            bindingResult
                    .getFieldErrors()
                    .forEach(
                            error -> model.addAttribute(error.getField(), error.getDefaultMessage())
                    );

            model.addAttribute("nomeInformado", cidadeDTO.getNome());
            model.addAttribute("estadoInformado", cidadeDTO.getEstado());
            model.addAttribute("cidadeList", cidadeService.buscarTodasCidades());

            return "/crud";
        } else {

            cidadeService.salvarCidade(cidadeDTO);
        }

        return "redirect:/";
    }

    @GetMapping("/excluir")
    public String excluir(@RequestParam String nome, @RequestParam String estado, HttpServletResponse response) {

        response.addCookie(new Cookie("excluir", LocalDateTime.now().toString()));
        cidadeService.deletarCidade(nome, estado);
        return "redirect:/";
    }

    @GetMapping("/prepararAlterar")
    public String prepararAlteracao(@RequestParam String nome, @RequestParam String estado, Model model) {

        var cidadeAtual = cidadeService.buscarCidadeByNomeAndEstado(nome, estado);

        if (cidadeAtual != null) {

            model.addAttribute("cidadeAtual", cidadeAtual);
            model.addAttribute("cidadeList", cidadeService.buscarTodasCidades());
        }

        return "/crud";
    }

    @PostMapping("/alterar")
    public String alterar(@RequestParam String nomeAtual, @RequestParam String estadoAtual, CidadeDTO cidade, BindingResult bindingResult, Model model, HttpServletResponse response) {

        response.addCookie(new Cookie("alterar", LocalDateTime.now().toString()));
        var cidadeAtual = cidadeService.buscarCidadeByNomeAndEstado(nomeAtual, estadoAtual);

        if (cidadeAtual != null) {

            var cidadeEncontrada = cidadeAtual;
            cidadeEncontrada.setNome(cidade.getNome());
            cidadeEncontrada.setEstado(cidade.getEstado());

            cidadeService.salvarCidade(cidadeEncontrada);
        }

        return "redirect:/";
    }

    @GetMapping("/mostrar")
    @ResponseBody
    public String mostrarCookieAlterar(@CookieValue String listar) {

        return "Último acesso ao método listar(): " + listar;
    }
}
