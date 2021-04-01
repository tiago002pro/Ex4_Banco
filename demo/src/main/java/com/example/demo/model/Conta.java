package com.example.demo.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Setter
@Getter
@Table(name = "conta")
public class Conta implements Banco{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "tipo_conta")
    private String tipo_conta;

    @Column(name = "tipo_do_cliente")
    private String tipo_do_cliente;

    @Column(name = "ag")
    private Integer ag;

    @Column(name = "conta")
    private Integer conta;

    @Column(name = "saldo")
    private Double saldo;

    @OneToOne
    @JoinColumn(name = "id_cliente", referencedColumnName = "id")
    private Cliente cliente;

}
