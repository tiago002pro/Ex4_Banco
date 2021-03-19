package com.example.demo.model;

public class ClientePF extends Cliente{
    private String cpf;
    private String rg;

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }
    public String getCpf() {
        return cpf;
    }
    public void setRg(String rg) {
        this.rg = rg;
    }
    public String getRg() {
        return rg;
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
