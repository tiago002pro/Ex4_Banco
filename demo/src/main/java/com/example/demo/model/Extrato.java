package com.example.demo.model;

import lombok.*;

import javax.persistence.*;
import java.util.Date;

@Entity
@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Extrato {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "conta_tipo")
    private String contaTipo;

    @Column(name = "data")
    private Date data;

    @Column(name = "descricao")
    private String descricao;

    @Column(name = "saldo")
    private Double saldo;

    @Column(name = "valor")
    private Double valor;

    @Column(name = "novo_saldo")
    private Double novoSaldo;

    @Column(name = "cheque_especial")
    private Double chequeEspecial;

    @Column(name = "juros")
    private Double juros;

    @ManyToOne
    @JoinColumn(name = "id_cliente", referencedColumnName = "id")
    private Cliente cliente;

    public Extrato(
            String contaTipo,
            Date data,
            String descricao,
            Double saldo,
            Double valor,
            Double novoSaldo,
            Double chequeEspecial,
            Double juros,
            Cliente cliente) {
        this.contaTipo = contaTipo;
        this.data = data;
        this.descricao = descricao;
        this.saldo = saldo;
        this.valor = valor;
        this.novoSaldo = novoSaldo;
        this.chequeEspecial = chequeEspecial;
        this.juros = juros;
        this.cliente = cliente;
    }

}
