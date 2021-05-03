package com.example.demo.service;

import com.example.demo.model.Conta;
import com.example.demo.repository.ContaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class BancoService {

    @Autowired
    ContaService contaService;

    @Autowired
    ContaRepository contaRepository;

    @Autowired
    ExtratoService extratoService;

    public String saque(Long idConta, Map<String, Object> json) {
        Conta conta = contaService.getContaId(idConta);
        double valor = ((Double) json.get("valor"));

        if (conta.getTipo_conta().equals("Corrente")) {
            if(conta.saque(conta, valor) != null) {
                extratoService.registra_saque(conta, valor);
                this.contaRepository.save(conta);
                return "Saque efetuado.";
            } else {
                return "Saldo insulficiente";
            }
        } else {
            if(conta.saque(conta, valor) != null) {
                extratoService.registra_saque(conta, valor);
                this.contaRepository.save(conta);
                return "Saque efetuado.";
            } else {
                return "Saldo insulficiente";
            }
        }
    }

    public String deposito(Long idConta, Map<String, Object> json) {
        Conta conta = contaService.getContaId(idConta);
        double valor = ((Double) json.get("valor"));

        conta.deposito(conta, valor);
        extratoService.registra_deposito(conta, valor);
        this.contaRepository.save(conta);
        return "Depósito efetuado.";
    }

    public String transferencia(Long idContaOrigem, Long idContaDestino, Map<String, Object> json) {
        Conta contaOrigem = contaService.getContaId(idContaOrigem);
        Conta contaDestino = contaService.getContaId(idContaDestino);
        double valor = ((Double) json.get("valor"));

        if (contaOrigem.saque(contaOrigem, valor) == null) {
            return "Saldo insulficiente.";
        } else {
            contaDestino.deposito(contaDestino, valor);
            extratoService.registra_transferencia(contaOrigem, contaDestino, valor);
            this.contaRepository.save(contaOrigem);
            this.contaRepository.save(contaDestino);
            return "Transação efutuada.";
        }
    }
}
