package com.example.demo.service;

import com.example.demo.model.Conta;
import com.example.demo.model.Extrato;
import com.example.demo.repository.ContaRepository;
import com.example.demo.repository.ExtratoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
public class ExtratoService {

    @Autowired
    ExtratoRepository repository;

    @Autowired
    ContaRepository contaRepository;

    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");

    public void registraSaque(Conta conta, Double valor) throws ParseException {
        List<Extrato> extratoConta = conta.getLancamentoExtrato();
        Extrato extrato = Extrato.builder()
                .contaTipo(conta.getContaTipo())
                .data(simpleDateFormat.parse(simpleDateFormat.format(new Date())))
                .descricao("Saque")
                .saldo(conta.getSaldo() + valor)
                .valor(valor * (-1))
                .novoSaldo(conta.getSaldo() - valor)
                .chequeEspecial(conta.getChequeEspecial())
                .juros(conta.getJuros())
                .cliente(conta.getCliente())
                .build();
        extratoConta.add(extrato);
        this.repository.save(extrato);
        this.contaRepository.save(conta);
    }

    public void registraDeposito(Conta conta, Double valor) throws ParseException{
        List<Extrato> extratoConta = conta.getLancamentoExtrato();
        Extrato extrato = Extrato.builder()
                .contaTipo(conta.getContaTipo())
                .data(simpleDateFormat.parse(simpleDateFormat.format(new Date())))
                .descricao("Depósito")
                .saldo(conta.getSaldo() - valor)
                .valor(valor)
                .novoSaldo(conta.getSaldo() + valor)
                .chequeEspecial(conta.getChequeEspecial())
                .juros(conta.getJuros())
                .cliente(conta.getCliente())
                .build();
        extratoConta.add(extrato);
        this.repository.save(extrato);
    }

    public void registraTransferencia(Conta contaOrigem, Conta contaDestino, Double valor) throws ParseException{
        List<Extrato> ListExtratoContaOrigem = contaOrigem.getLancamentoExtrato();
        Extrato extratoContaOrigem = Extrato.builder()
                .contaTipo(contaOrigem.getContaTipo())
                .data(simpleDateFormat.parse(simpleDateFormat.format(new Date())))
                .descricao("Transferência Efetuada")
                .saldo(contaOrigem.getSaldo() + valor)
                .valor(valor * (-1))
                .novoSaldo(contaOrigem.getSaldo() - valor)
                .chequeEspecial(contaOrigem.getChequeEspecial())
                .juros(contaOrigem.getJuros())
                .cliente(contaOrigem.getCliente())
                .build();
        ListExtratoContaOrigem.add(extratoContaOrigem);
        this.repository.save(extratoContaOrigem);

        List<Extrato> ListExtratoContaDestino = contaDestino.getLancamentoExtrato();
        Extrato extratoContaDestino = Extrato.builder()
                .contaTipo(contaDestino.getContaTipo())
                .data(simpleDateFormat.parse(simpleDateFormat.format(new Date())))
                .descricao("ransferência Recebida")
                .saldo(contaDestino.getSaldo() - valor)
                .valor(valor)
                .novoSaldo(contaDestino.getSaldo() + valor)
                .chequeEspecial(contaDestino.getChequeEspecial())
                .juros(contaDestino.getJuros())
                .cliente(contaDestino.getCliente())
                .build();
        ListExtratoContaDestino.add(extratoContaDestino);
        this.repository.save(extratoContaDestino);
    }

    public List<Extrato> extratoCliente(Long idCliente, Character tipoConta) {
        if (tipoConta == 'c') {
            return this.repository.findExtratoCliente("corrente", idCliente);
        } else if (tipoConta == 'p') {
            return this.repository.findExtratoCliente("poupanca", idCliente);
        } else {
            return null;
        }
    }

    public Page<Extrato> extratoConta(Long idConta, Pageable page) {
        return this.repository.findExtratoByConta(idConta, page);
    }
}
