package com.example.Mensajeria.model;

import javax.persistence.*;

@Entity
@Table(name = "paquetes")
public class Paquete {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idPaquete;
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TipoPaquete tipoPaquete;

    @Column(nullable = false)
    private double peso;

    @Column(nullable = false)
    private double valorDeclarado;



    public Paquete() {}


    public Paquete(TipoPaquete tipoPaquete, double peso, double valorDeclarado) {
        this.tipoPaquete = tipoPaquete;
        this.peso = peso;
        this.valorDeclarado = valorDeclarado;
    }

    public TipoPaquete getTipoPaquete() {
        return this.tipoPaquete;
    }

    public void setTipoPaquete(TipoPaquete tipoPaquete) {
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