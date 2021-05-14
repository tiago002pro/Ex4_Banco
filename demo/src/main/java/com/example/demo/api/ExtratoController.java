package com.example.demo.api;

import com.example.demo.model.Extrato;
import com.example.demo.service.ExtratoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/extrato")
public class ExtratoController {

    @Autowired
    ExtratoService service;

    @CrossOrigin
    @GetMapping("/cliente/{idCliente}/c{tipoConta}")
    public List<Extrato> extrato_cliente(@PathVariable Long idCliente, @PathVariable Character tipoConta) {
        return this.service.extratoCliente(idCliente, tipoConta);
    }

    @CrossOrigin
    @GetMapping("/conta/{idConta}")
    public Page<Extrato> extratoConta(@PathVariable Long idConta, Pageable page) {
        return this.service.extratoConta(idConta, page);
    }
}
