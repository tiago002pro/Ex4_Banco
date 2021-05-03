package com.example.demo.api;

import com.example.demo.model.Extrato;
import com.example.demo.service.ExtratoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ExtratoController {

    @Autowired
    ExtratoService service;

    @GetMapping("/extrato/cliente/{idCliente}/c{tipoConta}")
    public List<Extrato> extrato_cliente(@PathVariable Long idCliente, @PathVariable Character tipoConta) {
        return this.service.extrato_cliente(idCliente, tipoConta);
    }
}
