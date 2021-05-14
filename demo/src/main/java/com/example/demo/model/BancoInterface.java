package com.example.demo.model;

import com.example.demo.repository.ExtratoRepository;
import com.example.demo.service.ExtratoService;
import liquibase.pro.packaged.L;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public interface BancoInterface {


    default void calcula_juros(Conta conta, Extrato extrato) {
        if (conta.getSaldo() < 0) {
            conta.setJuros(conta.getJuros() + (1000 + conta.getSaldo() ) * 0.10);
        }
    }

    default Double deposito(Conta conta, double valor) {
        double novo_saldo = conta.getSaldo() + valor;
        conta.setSaldo(novo_saldo);

        if(conta.getSaldo() >= 0 && conta.getContaTipo().equals("corrente")) {
            conta.setChequeEspecial(1000.00);
        }
        if (conta.getSaldo() < 0 && conta.getContaTipo().equals("corrente")) {
            conta.setChequeEspecial(conta.getSaldo()+1000);
        }
        return novo_saldo;
    }

    default Double saque(Conta conta, double valor){
        double saldo = conta.getSaldo();

        if(saldo >= valor) {
            saldo = saldo - valor;
            conta.setSaldo(saldo);
            if (saldo < 0 && conta.getContaTipo().equals("corrente")) {
                conta.setChequeEspecial(conta.getSaldo()+1000);
            }
            return saldo;
        } else if (saldo < valor && libera_limite(conta)){
            if (libera_valor_limite(saldo, valor)) {
                saldo = saldo - valor;
                conta.setSaldo(saldo);
                if (saldo < 0 && conta.getContaTipo().equals("corrente")) {
                    conta.setChequeEspecial(conta.getSaldo()+1000);
                }
                return saldo;
            } else {
                return null;
            }
        } else {
            return null;
        }
    }

    default Boolean libera_limite(Conta conta) {
        return conta.getContaTipo().equals("corrente");
    }

    default  Boolean libera_valor_limite(double saldo, double valor) {
        return saldo - valor + 1000.00 >= 0;
    }

//    default Double calculaJuros(List<Extrato> extrato, Extrato extrato2, Extrato extrato3) {
//        for (int x=0; x<extrato.size(); x++) {
//            if (extrato2.getData().equals(extrato3.getData())) {
//                extrato2.get
//            } else {
//                double juros = extrato2.getNovo_saldo() * 0.10;
//            }
//
//        }
//
//    }

//    public List<Extrato> extrato_cliente(Long idCliente, Character tipoConta) {
//        if (tipoConta == 'c') {
//            return this.repository.findExtratoCliente("corrente", idCliente);
//        } else if (tipoConta == 'p') {
//            return this.repository.findExtratoCliente("poupanca", idCliente);
//        } else {
//            return null;
//        }
//    }

    //
    // se o saldo < 0
    // data_uso_limite pega a data atual (do saque)
    // cont dias = data_uso_limite - data_hoje (saque e deposito) = retorna quantidade de dias
    // juros = saldo * 10%
    // juros = juros * dias

    // deposito
    // saldo = saldo - juros
    //



//    void extrato(Conta conta);
}
