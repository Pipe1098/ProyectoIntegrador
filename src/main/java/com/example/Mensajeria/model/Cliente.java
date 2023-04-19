package com.example.Mensajeria.model;

import net.bytebuddy.asm.Advice;

import javax.persistence.*;

@Entity
@Table(name = "clientes")
public class Cliente extends Usuario {

    @Column(nullable = false)
    private String direccionEnvio;

    // Constructor vacío requerido por JPA
    public Cliente() {}



    // Constructor con parámetros
    public Cliente(String nombre, String apellido, String celular, String correo, String direccion, String ciudad, String cedula, String direccionEnvio) {
        super(nombre, apellido, celular, correo, direccion, ciudad, cedula);
        this.direccionEnvio = direccionEnvio;
    }

    public String getDireccionEnvio() {
        return direccionEnvio;
    }

    public void setDireccionEnvio(String direccionEnvio) {
        this.direccionEnvio = direccionEnvio;
    }

    public void setNombre(String nombre) {
        this.nombre=nombre;
    }
}

