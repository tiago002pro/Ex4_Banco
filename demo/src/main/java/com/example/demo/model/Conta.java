package com.example.demo.model;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "conta")
public class Conta implements BancoInterface {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "conta_tipo")
    private String contaTipo;

    @Column(name = "agencia")
    private Integer agencia;

    @Column(name = "conta")
    private Integer conta;

    @Column(name = "saldo")
    private Double saldo;

    @Column(name = "cheque_especial")
    private Double chequeEspecial;

    @Column(name = "juros")
    private Double juros;

    @OneToOne
    @JoinColumn(name = "id_cliente", referencedColumnName = "id")
    private Cliente cliente;

    @OneToMany(cascade = CascadeType.MERGE)
    @JoinColumn(name = "id_conta", referencedColumnName = "id")
    private List<Extrato> lancamentoExtrato;

    public Conta(
            String contaTipo,
            Integer agecia,
            Integer conta,
            Double saldo,
            Double juros,
            Double chequeEspecial,
            Cliente cliente,
            List<Extrato> lancamentoExtrato) {
        this.contaTipo = contaTipo;
        this.agencia = agecia;
        this.conta = conta;
        this.saldo = saldo;
        this.juros = juros;
        this.chequeEspecial = chequeEspecial;
        this.cliente = cliente;
        this.lancamentoExtrato = lancamentoExtrato;
    }

}
