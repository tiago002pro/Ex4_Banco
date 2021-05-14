package com.example.demo.api;

import com.example.demo.model.Conta;
import com.example.demo.service.ContaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.swing.text.html.parser.Entity;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/contas")
public class ContaController {

    @Autowired
    ContaService service;

    @CrossOrigin
    @PostMapping("/c{tipoConta}/clientes/{idCliente}")
    public ResponseEntity criaConta(@PathVariable Long idCliente, @PathVariable Character tipoConta) {
        return ResponseEntity.ok(this.service.criaConta(idCliente, tipoConta));
    }

    @CrossOrigin
    @GetMapping
    public List<Conta> getContas() {
        return this.service.getContas();
    }

    @CrossOrigin
    @GetMapping("/clientes/{id}")
    public List<Conta> getContas(@PathVariable Long id) {
        return this.service.getContas(id);
    }

    @CrossOrigin
    @GetMapping("/c{tipoConta}/cliente/{idCliente}")
    public List<Conta> contaCliente(@PathVariable Long idCliente, @PathVariable Character tipoConta) {
        return this.service.contaCliente(idCliente, tipoConta);
    }

    @CrossOrigin
    @GetMapping("/{id}")
    public Conta getContaId(@PathVariable Long id) {
        return this.service.getContaId(id);
    }

}
