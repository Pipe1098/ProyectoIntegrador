package com.example.Mensajeria.dto;

public class PaqueteDTO {

    private double peso;
    private double valorDeclarado;
    private String tipoPaquete;


    public PaqueteDTO() {
    }

    public PaqueteDTO(double peso, double valorDeclarado) {
        this.peso = peso;
        this.valorDeclarado = valorDeclarado;
    }
    public PaqueteDTO(double peso, double valorDeclarado,String tipoPaquete) {
        this.peso = peso;
        this.valorDeclarado = valorDeclarado;
        this.tipoPaquete = tipoPaquete;
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

    public String getTipoPaquete() {
        return this.tipoPaquete;
    }
}
