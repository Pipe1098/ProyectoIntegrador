package com.example.Mensajeria.model;

import javax.persistence.*;

@Entity
@Table(name = "paquetes")
public class Paquete {

    @Id
    private String idPaquete;

    @Column(nullable = false)
    private String tipoPaquete;

    @Column(nullable = false)
    private double peso;

    @Column(nullable = false)
    private double valorDeclarado;

    // Constructor vacío requerido por JPA
    public Paquete() {}

    // Constructor con parámetros
    public Paquete(String idPaquete, String tipoPaquete, double peso, double valorDeclarado) {
        this.idPaquete = idPaquete;
        this.tipoPaquete = tipoPaquete;
        this.peso = peso;
        this.valorDeclarado = valorDeclarado;
    }

    public String getIdPaquete() {
        return idPaquete;
    }

    public void setIdPaquete(String idPaquete) {
        this.idPaquete = idPaquete;
    }

    public String getTipoPaquete() {
        return tipoPaquete;
    }

    public void setTipoPaquete(String tipoPaquete) {
        this.tipoPaquete = tipoPaquete;
    }

    public double getPeso() {
        return peso;
    }

    public void setPeso(double peso) {
        this.peso = peso;
    }

    public double getValorDeclarado() {
        return valorDeclarado;
    }

    public void setValorDeclarado(double valorDeclarado) {
        this.valorDeclarado = valorDeclarado;
    }
}