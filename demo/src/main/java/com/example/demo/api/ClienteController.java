package com.example.demo.api;

import com.example.demo.model.Cliente;
import com.example.demo.service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
public class ClienteController {

    @Autowired
    ClienteService service;

    @PostMapping("/clientes/p{pessoa}")
    public String cadastraCliente(@PathVariable Character pessoa, @RequestBody Map<String, String> json) {
        return this.service.cadastraCliente(pessoa, json);
    }

    @GetMapping("/clientes")
    public List<Cliente> getClientes() {
        return this.service.getClientes();
    }

    @GetMapping("/cliente/{id}")
    public Cliente getCliente(@PathVariable Long id) {
        return this.service.getCliente(id);
    }

    @GetMapping("/clientes/p{pessoa}")
    public List<? extends Cliente> pegaListaClientes(@PathVariable Character pessoa) {
       return this.service.pegaListaClientes(pessoa);
    }

}
