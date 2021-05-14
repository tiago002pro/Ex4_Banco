package com.example.demo.model;

import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "cliente_pf")
public class ClientePF extends Cliente{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "cpf")
    private String cpf;

    @Column(name = "rg")
    private String rg;

    public ClientePF(String clienteTipo, String nome, String endereco, String cpf, String rg) {
        super(clienteTipo, nome, endereco);
        this.cpf = cpf;
        this.rg = rg;
    }
}
