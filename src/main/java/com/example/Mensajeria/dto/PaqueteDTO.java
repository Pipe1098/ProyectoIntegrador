package com.example.Mensajeria.dto;

import com.example.Mensajeria.model.TipoPaquete;

public class PaqueteDTO {

    private double peso;
    private double valorDeclarado;
    private TipoPaquete tipoPaquete;


    public PaqueteDTO() {
    }

    public PaqueteDTO(double peso, double valorDeclarado) {
        this.peso = peso;
        this.valorDeclarado = valorDeclarado;
    }
    public PaqueteDTO(double peso, double valorDeclarado,TipoPaquete tipoPaquete) {
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

    public TipoPaquete getTipoPaquete() {
        return this.tipoPaquete;
    }
}
