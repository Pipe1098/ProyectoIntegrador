package com.example.Mensajeria.model;

import javax.persistence.*;

@Entity
@Table(name = "paquetes")
public class Paquete {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idPaquete;

    @Column(nullable = false)
    private String tipoPaquete;

    @Column(nullable = false)
    private double peso;

    @Column(nullable = false)
    private double valorDeclarado;

    // Constructor vacío requerido por JPA
    public Paquete() {}

    // Constructor con parámetros
    public Paquete(String tipoPaquete, double peso, double valorDeclarado) {
        this.tipoPaquete = tipoPaquete;
        this.peso = peso;
        this.valorDeclarado = valorDeclarado;
    }

    public enum TipoPaquete {

        LIVIANO(1),
        MEDIANO(2),
        GRANDE(3);

        private int valor;

        TipoPaquete(int valor) {
            this.valor = valor;
        }
    }
    public String getTipoPaquete() {
        return this.tipoPaquete;
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