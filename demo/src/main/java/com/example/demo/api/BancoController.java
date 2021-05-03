package com.example.demo.api;

import com.example.demo.service.BancoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class BancoController {

    @Autowired
    BancoService service;

    @PostMapping("/banco/saque/conta/{idConta}")
    public String saque(@PathVariable Long idConta, @RequestBody Map<String, Object> json) {
        return this.service.saque(idConta, json);
    }

    @PostMapping("/banco/deposito/conta/{idConta}")
    public String deposito(@PathVariable Long idConta, @RequestBody Map<String, Object> json) {
        return this.service.deposito(idConta, json);
    }

    @PostMapping("/banco/transferencia/contaOrigem/{idContaOrigem}/contaDestino/{idContaDestino}")
    public String transferencia(@PathVariable Long idContaOrigem, @PathVariable Long idContaDestino, @RequestBody Map<String, Object> json) {
        return this.service.transferencia(idContaOrigem, idContaDestino, json);
    }
}
