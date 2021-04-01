package com.example.demo.service;

import com.example.demo.model.Banco;
import com.example.demo.model.Cliente;
import com.example.demo.model.Conta;
import com.example.demo.repository.ContaRepository;
import liquibase.pro.packaged.B;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class BancoService {

    @Autowired
    ClienteService clienteService;

    @Autowired
    ContaService contaService;

    @Autowired
    ContaRepository repository;

    public String saque(Long id_conta, Map<String, Object> json) {
        Conta conta = contaService.getContaId(id_conta);

        if(conta.saque(conta, (Double) json.get("Valor") ) == null) {
            return "Saldo insulficiente";
        } else {
            this.repository.save(conta);
            return "Saque efetuado.";
        }
    }

    public String deposito(Long id_conta, Map<String, Object> json) {
        Conta conta = contaService.getContaId(id_conta);

        conta.deposito(conta, (Double) json.get("Valor"));
        this.repository.save(conta);
        return "Depósito efetuado.";
    }

    public String transferencia(Long id_conta_origem, Long id_conta_destino, Map<String, Object> json) {
        Conta conta_origem = contaService.getContaId(id_conta_origem);
        Conta conta_destino = contaService.getContaId(id_conta_destino);
        double valor = ((Double) json.get("Valor"));

        if (conta_origem.saque(conta_origem, valor) == null) {
            return "Saldo insulficiente.";
        } else {
            conta_destino.deposito(conta_destino, valor);
            this.repository.save(conta_origem);
            this.repository.save(conta_destino);
            return "Transação efutuada.";
        }

    }
}
