package com.example.demo.service;

import com.example.demo.model.Cliente;
import com.example.demo.model.Conta;
import com.example.demo.repository.ContaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;

@Service
public class ContaService {

    @Autowired
    ContaRepository repository;

    @Autowired
    ClienteService clienteService;

    Random random = new Random();

    public String criaConta(Long idCliente, Character tipoConta, Map<String, Object> json) {
        Cliente cliente = clienteService.getCliente(idCliente);
        Conta conta = new Conta();

        if (tipoConta == 'c') {
            if (verificarContasExistentes(idCliente, tipoConta)) {
                return "Só é permitido ter uma conta corrente!";
            } else {
                conta.setTipo_conta("corrente");
                conta.setCheque_especial(1000.00);
            }
        }

        else if (tipoConta == 'p') {
            if (verificarContasExistentes(idCliente, tipoConta)) {
                return "Só é permitido ter uma conta poupança";
            } else {
                conta.setTipo_conta("poupanca");
                conta.setCheque_especial(0.0);
            }
        }

        conta.setCliente(cliente);
        conta.setAg(2525);
        conta.setConta(new Random().nextInt(99999) + 10000);
        conta.setSaldo(0.0);
        conta.setJuros(0.0);
        conta.setLancamento_extrato(new ArrayList<>());
        this.repository.save(conta);
        return "Conta cadastrada com sucesso!";
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

    public Boolean verificarContasExistentes(Long id_cliente, Character tipo_conta) {

        if (tipo_conta == 'c') {
            return (this.repository.findAllCliente(id_cliente, "corrente").size() > 0);
        }
        else if (tipo_conta == 'p') {
            return (this.repository.findAllCliente(id_cliente, "poupanca").size() > 0);
        }
        else {
            return false;
        }
    }
}
