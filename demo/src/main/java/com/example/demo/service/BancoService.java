package com.example.demo.service;

import com.example.demo.model.Conta;
import com.example.demo.model.Message;
import com.example.demo.repository.ContaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.util.Map;

@Service
public class BancoService {

    @Autowired
    ContaService contaService;

    @Autowired
    ContaRepository contaRepository;

    @Autowired
    ExtratoService extratoService;

    public String saque(Long idConta, Map<String, String> json) throws ParseException {
        Conta conta = contaService.getContaId(idConta);
        Double valor = Double.parseDouble(json.get("valor"));


        if (conta.getContaTipo().equals("Corrente")) {
            if(conta.saque(conta, valor) != null) {
                extratoService.registraSaque(conta, valor);
                this.contaRepository.save(conta);
                return "Saque efetuado.";
            } else {
                return "Saldo insulficiente";
            }
        } else {
            if(conta.saque(conta, valor) != null) {
                extratoService.registraSaque(conta, valor);
                this.contaRepository.save(conta);
                return "Saque efetuado.";
            } else {
                return "Saldo insulficiente";
            }
        }
    }

    public String deposito(Long idConta, Map<String, String> json) throws ParseException {
        Conta conta = contaService.getContaId(idConta);
        Double valor = Double.parseDouble(json.get("valor"));

        conta.deposito(conta, valor);
        extratoService.registraDeposito(conta, valor);
        this.contaRepository.save(conta);
        return "Depósito efetuado.";
    }

    public String transferencia(Long idContaOrigem, Long idContaDestino, Map<String, String> json) throws ParseException {
        Conta contaOrigem = contaService.getContaId(idContaOrigem);
        Conta contaDestino = contaService.getContaId(idContaDestino);
        Double valor = Double.parseDouble(json.get("valor"));

        if (contaOrigem.saque(contaOrigem, valor) == null) {
            return "Saldo insulficiente.";
        } else {
            contaDestino.deposito(contaDestino, valor);
            extratoService.registraTransferencia(contaOrigem, contaDestino, valor);
            this.contaRepository.save(contaOrigem);
            this.contaRepository.save(contaDestino);
            return "Transação efutuada.";
        }
    }
}
