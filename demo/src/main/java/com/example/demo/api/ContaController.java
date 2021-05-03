package com.example.demo.api;

import com.example.demo.model.Conta;
import com.example.demo.service.ContaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
public class ContaController {

    @Autowired
    ContaService service;

    @PostMapping("/contas/c{tipoConta}/clientes/{idCliente}")
    public String criaConta(@PathVariable Long idCliente, @PathVariable Character tipoConta, @RequestBody Map<String, Object> json) {
        return this.service.criaConta(idCliente, tipoConta, json);
    }

    @GetMapping("/contas")
    public List<Conta> getContas() {
        return this.service.getContas();
    }

    @GetMapping("/contas/clientes/{id}")
    public List<Conta> getContas(@PathVariable Long id) {
        return this.service.getContas(id);
    }

    @GetMapping("/contas/{id}")
    public Conta getContaId(@PathVariable Long id) {
        return this.service.getContaId(id);
    }

}
