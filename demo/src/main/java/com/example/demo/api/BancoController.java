package com.example.demo.api;

import com.example.demo.service.BancoService;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.Map;

@RestController
@RequestMapping("/banco")
public class BancoController {

    @Autowired
    BancoService service;

    @CrossOrigin
    @PostMapping("/saque/conta/{idConta}")
    public String saque(@PathVariable Long idConta, @RequestBody Map<String, String> json) throws ParseException {
        return this.service.saque(idConta, json);
    }

    @CrossOrigin
    @PostMapping("/deposito/conta/{idConta}")
    public String deposito(@PathVariable Long idConta, @RequestBody Map<String, String> json) throws ParseException {
        return this.service.deposito(idConta, json);
    }

    @CrossOrigin
    @PostMapping("/transferencia/contaOrigem/{idContaOrigem}/contaDestino/{idContaDestino}")
    public String transferencia(@PathVariable Long idContaOrigem, @PathVariable Long idContaDestino, @RequestBody Map<String, String> json) throws ParseException {
        return this.service.transferencia(idContaOrigem, idContaDestino, json);
    }
}
