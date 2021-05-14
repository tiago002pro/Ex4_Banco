package com.example.demo.model;

import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "cliente_pj")
public class ClientePJ extends Cliente{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "cnpj")
    private String cnpj;

    public ClientePJ(String clienteTipo, String nome, String endereco, String cnpj) {
        super(clienteTipo, nome, endereco);
        this.cnpj = cnpj;
    }
}
