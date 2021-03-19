package com.example.demo.model;

public class Cliente {
    private Pessoa pessoa;
    private String nome;
    private String endereco;
//    private String bairro;
//    private String cep;
//    private String cidade;
//    private String estado


    public void setPessoa(Pessoa pessoa) {
        this.pessoa = pessoa;
    }

    public Pessoa getPessoa() {
        return pessoa;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
    public String getNome() {
        return nome;
    }
    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }
    public String getEndereco() {
        return endereco;
    }
}
