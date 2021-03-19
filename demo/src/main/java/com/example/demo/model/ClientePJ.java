package com.example.demo.model;

public class ClientePJ extends Cliente{
    private String nomeFantasia;
    private String cnpj;

    public void setNomeFantasia(String nomeFantasia) {
        this.nomeFantasia = nomeFantasia;
    }
    public String getNomeFantasia() {
        return nomeFantasia;
    }
    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }
    public String getCnpj() {
        return cnpj;
    }

    @Override
    public Pessoa getPessoa() {
        return super.getPessoa();
    }
    @Override
    public String getNome() {
        return super.getNome();
    }
    @Override
    public String getEndereco() {
        return super.getEndereco();
    }
}
