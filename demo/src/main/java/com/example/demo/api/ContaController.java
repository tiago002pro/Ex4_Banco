package com.example.demo.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class ContaController {

    @Autowired
        ContaService service;

//    @PostMapping("conta/c{tipoConta}/cliente/p{pessoa}")
//    public String criaConta(@PathVariable Character pessoa, @PathVariable Integer id, @PathVariable Character tipoConta, @RequestBody Map<String, Object> json) {
//        return this.service.criaConta(pessoa, id, tipoConta, json);
//    }

//    @GetMapping("conta/cliente/p{tipoConta}")
}
