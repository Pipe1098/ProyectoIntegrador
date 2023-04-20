package com.example.Mensajeria.model;

import net.bytebuddy.asm.Advice;

import javax.persistence.*;

@Entity
@Table(name = "clientes")
public class Cliente extends Usuario {

    /*@Column(nullable = false)
    private String direccionEnvio;*/

    // Constructor vacío requerido por JPA
    public Cliente() {}



    // Constructor con parámetros
    public Cliente(String nombre, String apellido, String celular, String correo, String direccion, String ciudad, String cedula) {
        super(nombre, apellido, celular, correo, direccion, ciudad, cedula);

    }

   /* @Override
    public String getNombre() {
        return super.getNombre();
    }

    @Override
    public String getApellido() {
        return super.getApellido();
    }

    @Override
    public String getCelular() {
        return super.getCelular();
    }

    @Override
    public String getCorreo() {
        return super.getCorreo();
    }

    @Override
    public String getDireccion() {
        return super.getDireccion();
    }

    @Override
    public String getCiudad() {
        return super.getCiudad();
    }

    @Override
    public String getCedula() {
        return super.getCedula();
    }*/

/*    public String getDireccionEnvio() {
        return direccionEnvio;
    }

    public void setDireccionEnvio(String direccionEnvio) {
        this.direccionEnvio = direccionEnvio;
    }*/

    public void setNombre(String nombre) {
        this.nombre=nombre;
    }

}

