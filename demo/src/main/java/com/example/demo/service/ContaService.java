package com.example.demo.service;

import com.example.demo.model.Cliente;
import com.example.demo.model.Conta;
import com.example.demo.model.Extrato;
import com.example.demo.model.Message;
import com.example.demo.repository.ContaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Service
public class ContaService {

    @Autowired
    ContaRepository repository;

    @Autowired
    ClienteService clienteService;

    Random random = new Random();

    public Message criaConta(Long idCliente, Character tipoConta) {
        Cliente cliente = clienteService.getCliente(idCliente);
        Message message = new Message();

        if (tipoConta == 'c') {
            if (verificarContasExistentes(idCliente, tipoConta)) {
                message.setResponseError("Só é permitido ter uma conta corrente!");
                return message;
            } else {
                Conta conta = Conta.builder()
                        .contaTipo("corrente")
                        .agencia(2525)
                        .conta(random.nextInt(99999) + 10000)
                        .saldo(0.0)
                        .chequeEspecial(1000.00)
                        .cliente(cliente)
                        .juros(0.0)
                        .lancamentoExtrato(new ArrayList<>())
                        .build();
                this.repository.save(conta);
            }
        }

        else if (tipoConta == 'p') {
            if (verificarContasExistentes(idCliente, tipoConta)) {
                message.setResponseError("Só é permitido ter uma conta poupança");
                return message;
            } else {
                Conta conta = Conta.builder()
                        .contaTipo("poupanca")
                        .agencia(2525)
                        .conta(random.nextInt(99999) + 10000)
                        .saldo(0.0)
                        .chequeEspecial(0.0)
                        .juros(0.0)
                        .cliente(cliente)
                        .lancamentoExtrato(new ArrayList<>())
                        .build();
                this.repository.save(conta);
            }
        }
        message.setResponseSuccess("Conta cadastrada com sucesso!");
        return message;

    }

    public List<Conta> getContas() {
        return this.repository.findAll();
    }

    public List<Conta> getContas(Long idCliente) {
        return this.repository.findAllContaWithCliente(idCliente);
    }

    public Conta getContaId(Long id) {
        return this.repository.findById(id).get();
    }

    public Boolean verificarContasExistentes(Long idCliente, Character tipoConta) {

        if (tipoConta == 'c') {
            return (this.repository.findAllCliente(idCliente, "corrente").size() > 0);
        }
        else if (tipoConta == 'p') {
            return (this.repository.findAllCliente(idCliente, "poupanca").size() > 0);
        }
        else {
            return false;
        }
    }

    public Conta buscaContaPorTipo(Long idCliente, Character tipoConta) {

        if (tipoConta == 'c') {
            return this.repository.findConta(idCliente, "corrente");
        }
        else if (tipoConta == 'p') {
            return this.repository.findConta(idCliente, "poupanca");
        }
        else {
            return null;
        }
    }


    public List<Conta> contaCliente(Long idCliente, Character tipoConta) {
        if (tipoConta == 'c') {
            return this.repository.findContaCliente("corrente", idCliente);
        } else if (tipoConta == 'p') {
            return this.repository.findContaCliente("poupanca", idCliente);
        } else {
            return null;
        }
    }
}
