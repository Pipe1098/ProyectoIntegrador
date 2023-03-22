package com.example.usuarios.Model;

public class Paquete {

    private String idPaquete;
    private String tipoPaquete;
    private double peso;
    private double valorDeclarado;

    public Paquete(String idPaquete, String tipoPaquete, double peso, double valorDeclarado) {
        this.idPaquete = idPaquete;
        this.tipoPaquete = tipoPaquete;
        this.peso = peso;
        this.valorDeclarado = valorDeclarado;
    }

    public String getIdPaquete() {
        return idPaquete;
    }
}
