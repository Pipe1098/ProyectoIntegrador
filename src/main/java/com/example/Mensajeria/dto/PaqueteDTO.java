package com.example.Mensajeria.dto;

public class PaqueteDTO {

    private double peso;
    private double valorDeclarado;

    public PaqueteDTO() {
    }

    public PaqueteDTO(double peso, double valorDeclarado) {
        this.peso = peso;
        this.valorDeclarado = valorDeclarado;
    }

    public void setPeso(double peso) {
        this.peso = peso;
    }

    public void setValorDeclarado(double valorDeclarado) {
        this.valorDeclarado = valorDeclarado;
    }

    public double getPeso() {
        return peso;
    }

    public double getValorDeclarado() {
        return valorDeclarado;
    }
}
