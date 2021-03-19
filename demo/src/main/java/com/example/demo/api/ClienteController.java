package com.example.demo.api;

import com.example.demo.model.ClientePF;
import com.example.demo.model.ClientePJ;
import com.example.demo.service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
public class ClienteController {

    @Autowired
    ClienteService service;

    @PostMapping("/cliente/cadastro/P{pessoa}")
    public String cadastraCliente(@PathVariable Character pessoa, @RequestBody Map<String, String> json) {
        return this.service.cadastraCliente(pessoa, json);
    }

    @GetMapping("/clientes/P{pessoa}")
    public List pegaListaClientes(@PathVariable Character pessoa) {
       return this.service.pegaListaClientes(pessoa);
    }
}
