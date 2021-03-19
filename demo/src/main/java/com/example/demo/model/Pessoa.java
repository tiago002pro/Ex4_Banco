package com.example.demo.model;

public enum Pessoa {
    FISICA("Física"),
    JURIDICA("Juridica");

    private String tipo;

    Pessoa(String tipo) {
        this.tipo = tipo;
    }

    public String getTipo() {
        return tipo;
    }
}

