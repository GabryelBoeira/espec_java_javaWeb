package br.edu.utfpr.cp.espjava.crudcidade.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class CidadeController {

    @GetMapping("/")
    public String listar() {
        return "crud.html";
    }
}