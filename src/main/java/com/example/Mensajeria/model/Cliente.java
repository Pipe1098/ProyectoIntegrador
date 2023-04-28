package com.example.Mensajeria.model;


import javax.persistence.*;

@Entity
@Table(name = "clientes")
@PrimaryKeyJoinColumn(name = "id")
public class Cliente extends Usuario {

    public Cliente() {}

    public Cliente(String nombre, String apellido, String celular, String correo, String direccion, String ciudad, String cedula) {
        super(nombre, apellido, celular, correo, direccion, ciudad, cedula);

    }
    public void setNombre(String nombre) {
        this.nombre=nombre;
    }
}

