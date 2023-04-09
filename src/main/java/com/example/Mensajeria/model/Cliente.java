package com.example.Mensajeria.model;
import com.example.Mensajeria.model.Usuario;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "clientes")
public class Cliente extends Usuario {

    @Column(nullable = false)
    private String direccionEnvio;

    // Constructor vacío requerido por JPA
    public Cliente() {}

    @Override
    public void setNombre(String nombre) {
        this.nombre=nombre;
    }

    // Constructor con parámetros
    public Cliente(String nombre, String apellido, String celular, String correo, String direccion, String ciudad, Long cedula, String direccionEnvio) {
        super(nombre, apellido, celular, correo, direccion, ciudad, cedula);
        this.direccionEnvio = direccionEnvio;
    }

    public String getDireccionEnvio() {
        return direccionEnvio;
    }

    public void setDireccionEnvio(String direccionEnvio) {
        this.direccionEnvio = direccionEnvio;
    }

}
