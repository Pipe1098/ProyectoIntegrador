package com.example.Mensajeria.dto;

public class PaqueteDTO {
    private String idPaquete;
    private String tipoPaquete;
    private double peso;
    private double valorDeclarado;

    public PaqueteDTO(String idPaquete, String tipoPaquete, double peso, double valorDeclarado) {
        this.idPaquete = idPaquete;
        this.tipoPaquete = tipoPaquete;
        this.peso = peso;
        this.valorDeclarado = valorDeclarado;
    }

    public String getIdPaquete() {
        return idPaquete;
    }

    public String getTipoPaquete() {
        return tipoPaquete;
    }

    public double getPeso() {
        return peso;
    }

    public double getValorDeclarado() {
        return valorDeclarado;
    }
}
